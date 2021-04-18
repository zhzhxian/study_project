package com.zzx.esdemo02.service;

import com.alibaba.fastjson.JSON;
import com.zzx.esdemo02.entity.JdContent;
import com.zzx.esdemo02.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.XPackClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JdContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public boolean parseContent(String keyword) throws Exception {
        List<JdContent> jdContentList = HtmlParseUtil.parseJd(keyword);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for (JdContent jdContent : jdContentList) {
            bulkRequest.add(new IndexRequest("jd_content_list").source(JSON.toJSONString(jdContent), XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulkResponse.hasFailures();
    }

    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws Exception {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_content_list");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //精准搜索
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            list.add(searchHit.getSourceAsMap());

        }

        return list;
    }

    public List<Map<String, Object>> searchPageHighlight(String keyword, int pageNo, int pageSize) throws Exception {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_content_list");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //精准搜索
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮的字段
        highlightBuilder.field("title");
        //关闭多个高亮字段
        highlightBuilder.requireFieldMatch(false);
        //设置高亮的标签
        highlightBuilder.preTags("<span class='key' style='color:red'>");
        highlightBuilder.postTags("</span>");
        //绑定高亮搜索
        searchSourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        //解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            //解析高亮的字段
            Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
            HighlightField highlightField = highlightFieldMap.get("title");
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();//原来的结果
            //解析高亮的字段，将原来的字段换成解析出来的高亮字段
            if (highlightField != null) {
                Text[] fragments = highlightField.fragments();
                String newTitle = "";
                for (Text text : fragments) {
                    newTitle += text;
                }
                //使用高亮的字段值替换原来的字段值
                sourceAsMap.put("title", newTitle);
            }
            list.add(sourceAsMap);
        }
        return list;
    }
}
