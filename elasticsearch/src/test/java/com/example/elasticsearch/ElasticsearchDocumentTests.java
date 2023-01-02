package com.example.elasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ElasticsearchDocumentTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 创建文档
     * @throws IOException
     */
    @Test
    void contextLoads() throws IOException {
        IndexRequest indexRequest = new IndexRequest("order").id("2");
        //库中的映射
        indexRequest.source("{\n" +
                "  \"age\":\"22\",\n" +
                "  \"email\":\"2523121105@qq.com\",\n" +
                "  \"info\":\"这是一个不勤恳的程序员\"\n" +
                "}", XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Test
    void get() throws IOException {
        GetRequest getRequest = new GetRequest("order", "1");
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSource().toString());
    }

    @Test
    void update() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("order", "1");
        //局部修改
        updateRequest.doc(
                "age","27",
                "info","特别勤恳"
        );
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

    }

}
