package com.zzx.esdemo02.controller;

import com.zzx.esdemo02.service.JdContentService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdContentController {
    @Autowired
    private JdContentService jdContentService;

    @GetMapping("/parse/{keyword}")
    public boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        return jdContentService.parseContent(keyword);
    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String, Object>> searchPage(@PathVariable("keyword") String keyword, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) throws Exception {
        return jdContentService.searchPageHighlight(keyword, pageNo, pageSize);
    }

}
