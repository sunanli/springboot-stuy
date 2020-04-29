package com.tangwh.conrtoller;


import com.tangwh.service.ContentServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {


    @Autowired
    private ContentServcice contentServcice;


    @GetMapping("/parse/{keywords}")
    public Boolean parse(@PathVariable String keywords) throws IOException {

        Boolean aBoolean = contentServcice.parseContent(keywords);

        return aBoolean;

    }


    @GetMapping("/search/{keywords}/{pageNo}/{pageSize}")
    public List<Map<String, Object>>  string( @PathVariable("keywords") String keywords,
                          @PathVariable("pageNo")Integer pageNo,
                          @PathVariable("pageSize")Integer pageSize) throws IOException {
        return contentServcice.searchPageHighlightBuilder(keywords, pageNo, pageSize);
    }
}
