package com.tangwh;

import com.alibaba.fastjson.JSON;
import com.tangwh.pojo.User;
import com.tangwh.utils.Esconst;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 所有 ElasticSearch 7.6.1 的Api
 */
@SpringBootTest
class SpringbootElasticsearchApiApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient") // 对应自己指定的方法名
    private RestHighLevelClient client;

    //测试 创建索引的 Request == PUT howe_Index
    @Test
    void testCreateIndex() throws IOException {

        // 1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("howe_index");

        // 2. 客户端执行 请求 请求  后 获取响应
        CreateIndexResponse indices = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(indices);

    }

    // 测试获取索引(判读索引是否存在)
    @Test
    void testExistIndex() throws IOException {

        GetIndexRequest request = new GetIndexRequest("howe_index");

        // 判断是否存在
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        System.out.println(exists);


    }


    // 测试删除索引
    @Test
    void testDeleteIndex() throws IOException {

        DeleteIndexRequest request = new DeleteIndexRequest("howe_index");

        // 删除
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(delete.isAcknowledged());


    }

    // 添加文档

    @Test
    void testAddDocument() throws IOException {
        // 创建对象
        User user = new User("唐维豪", 18);
        //创建请求
        IndexRequest request = new IndexRequest("howe_index");

        // 规则 PUT/howe_index/_doc/1

        request.id("1");

        // 设置过期时间。
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 将我们数据放入请求 json
        request.source(JSON.toJSONString(user), XContentType.JSON);

        //客户端发送请求  获取响应结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status()); // 获取我们命令返回的状态 Create


    }


    // 获取文档 (判断是否存在)
    @Test
    void testExists() throws IOException {

        GetRequest request = new GetRequest("howe_index", "1");

        //不获取 返回的 _source上下文(可忽略)
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //获取文档信息
    @Test
    void testGetDocument() throws IOException {

        GetRequest request = new GetRequest("howe_index", "1");


        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 打印文档内容
        System.out.println(response.getSourceAsString());
        System.out.println(request);
    }


    //跟新文档信息
    @Test
    void testUpdateDocument() throws IOException {

        UpdateRequest request = new UpdateRequest("howe_index", "1");

        UpdateRequest updateRequest = new UpdateRequest("howe_index", "1");
        //过期时间
        updateRequest.timeout("1s");

        User user = new User("唐同学", 20);

        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);


        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.status());

    }

    // 删除文档信息
    @Test
    void testDeleteRequest() throws IOException {

        DeleteRequest request = new DeleteRequest("howe_index", "1");

        request.timeout("1s");
        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(delete.status());


    }

    // 特殊的 批量插入数据
    @Test
    void testBulkRequest() throws IOException {

        BulkRequest request = new BulkRequest();

        request.timeout("10s");

        List<User> users = new ArrayList<>();
        users.add(new User("唐同学1", 31));
        users.add(new User("唐同学2", 32));
        users.add(new User("唐同学3", 33));
        users.add(new User("唐同学4", 34));
        users.add(new User("唐同学5", 35));
        users.add(new User("唐同学6", 36));


        // 批处理请求
        for (int i = 0; i < users.size(); i++) {
                // 批量删除 批量修改 在这个修改相对应的请求
            request.add(new IndexRequest("howe_index")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(users.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(bulk.status());
        System.out.println(bulk);
    }

    // 查询

    @Test
    void testSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest(Esconst.ES_INDEX);
        // 构建搜索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();

        //构建查询 (精确查询) 可以使用QueryBuilders 
        // 工具类(精确匹配,) QueryBuilders.termQuery
        //QueryBuilders.matchAllQuery  匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", 33);
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        builder.query(termQueryBuilder);
        //分页
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(builder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(search.getHits()));
        System.out.println("===================");

        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());

        }



    }
}
