package com.zzx.esdemo01;

import com.alibaba.fastjson.JSON;
import com.zzx.esdemo01.entity.User;
import lombok.extern.slf4j.Slf4j;
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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EsDemo01ApplicationTests {


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public static final String INDEX = "es_demo01";

    /**
     * 创建索引
     *
     * @throws Exception
     */
    @Test
    void testCreateIndex() throws Exception {
        //创建请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX);
        //执行请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        log.info("创建索引结果:{}", createIndexResponse);
    }

    /**
     * 获取索引
     *
     * @throws Exception
     */
    @Test
    void testExistIndex() throws Exception {
        GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX);
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        log.info("索引是否存在:{}", exists);
    }


    /**
     * 删除索引
     *
     * @throws Exception
     */
    @Test
    void testDeleteIndex() throws Exception {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(INDEX);
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        log.info("删除索引:{}", acknowledgedResponse);
    }

    /**
     * 创建文档
     *
     * @throws Exception
     */
    @Test
    void testCreateDocument() throws Exception {
        //创建对象
        User user = new User("张三111", 32, "上海");
        //创建文档请求
        IndexRequest indexRequest = new IndexRequest(INDEX);
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("创建文档：{}", indexRequest);
        log.info("创建文件状态：{}", indexResponse.status());
    }

    /**
     * 文档是否存在
     *
     * @throws Exception
     */

    @Test
    void testExistDocument() throws Exception {
        //创建文档请求
        GetRequest getRequest = new GetRequest(INDEX, "1");
        //不获取返回结果中的上下文(_source)
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        log.info("文档是否存在：{}", exists);

    }

    /**
     * 获取文档信息
     *
     * @throws Exception
     */
    @Test
    void testGetDocument() throws Exception {
        //创建文档请求
        GetRequest getRequest = new GetRequest(INDEX, "1");
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        log.info("获取文档信息：{}", getResponse);
        log.info("文档的resource:{}", getResponse.getSource());
    }

    @Test
    void testUpdateDocument() throws Exception {
        //创建对象
        User user = new User("张三111", 34, "成都");
        UpdateRequest updateRequest = new UpdateRequest(INDEX, "1");
        updateRequest.timeout(TimeValue.timeValueSeconds(1));
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        log.info("更新文档的结果：{}", updateResponse);
        log.info("更新文档的结果status：{}", updateResponse.status());
    }

    /**
     * 删除文档
     *
     * @throws Exception
     */
    @Test
    void testDeleteDocument() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, "1");
        deleteRequest.timeout(TimeValue.timeValueSeconds(1));
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        log.info("删除结果:{}", deleteResponse.status());
    }

    /**
     * 批量创建文档
     */
    @Test
    void testBatchCreateDocument() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(60));
        for (int i = 2; i <= 1000; i++) {
            bulkRequest.add(new IndexRequest(INDEX).source(JSON.toJSONString(new User("李四" + i, i, "北京")), XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("批量插入是否失败：{}", bulkResponse.hasFailures());
    }

    @Test
    void testSearchDocument() throws Exception {
        SearchRequest searchRequest = new SearchRequest(INDEX);
        //构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("age", 2);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        log.info("文档搜索结果：{}", searchResponse);
    }
}
