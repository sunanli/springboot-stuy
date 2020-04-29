package com.tangwh.service;

import com.alibaba.fastjson.JSON;
import com.tangwh.pojo.Content;
import com.tangwh.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// 业务
@Service
public class ContentServcice {


    @Autowired
    private RestHighLevelClient restHighLevelClient;


    //  1.解析数据 放入es索引中

    public Boolean parseContent(String keywords) throws IOException {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);


        //插入到索引库中 es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        //添加操作
        for (int i = 0; i < contents.size(); i++) {

            bulkRequest.add(new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();


    }


    // 2. 获取这些数据 实现搜索功能
    // 分页
    public List<Map<String, Object>> searchPage(String keyword, Integer pageNo, Integer pageSize) throws IOException {

        if (pageNo <= 1) {
            pageNo = 0;
        }

        //条件搜索
        SearchRequest searchResult = new SearchRequest("jd_goods");


        // 构建搜索
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 分页 从那条数据开始
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);


        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行 搜索
        searchResult.source(sourceBuilder);


        SearchResponse searchResponse = restHighLevelClient.search(searchResult, RequestOptions.DEFAULT);

        //解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {


            list.add(documentFields.getSourceAsMap());

        }
        return list;
    }

    // 3. 获取这些数据 实现搜索功能 高亮搜索
    // 分页
    public List<Map<String, Object>> searchPageHighlightBuilder(String keyword, Integer pageNo, Integer pageSize) throws IOException {

        if (pageNo <= 1) {
            pageNo = 0;
        }

        //条件搜索
        SearchRequest searchResult = new SearchRequest("jd_goods");


        // 构建搜索
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 分页 从那条数据开始
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);


        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));



        //构建高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //高亮的内容
        highlightBuilder.field("title");
      // 高亮前缀的标签
        highlightBuilder.preTags("<span style='color:red'>");
        //是否要做多个高亮
        highlightBuilder.requireFieldMatch(false);
        // 高亮后缀的标签
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);


        //执行 搜索
        searchResult.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchResult, RequestOptions.DEFAULT);

        //解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {


            // 解析高亮的字段
            // 获取
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            // 原来的结果
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            // 解析高亮的字段 将原来的字段 和高亮 的字段 进行置换
            if (title!=null){

                Text[] fragments = title.fragments();
                String new_title = "";
                for (Text text : fragments) {
                    new_title += text;
                }
                //高亮字段 替换原来的字段
                sourceAsMap.put("title",new_title);

            }
            list.add(sourceAsMap);

        }
        return list;
    }


}
