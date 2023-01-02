package com.example.elasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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
class ElasticsearchIndexTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引库
     * @throws IOException
     */
    @Test
    void contextLoads() throws IOException {
        String json = "{\n" +
                "  \"mappings\":{\n" +
                "    \"properties\":{\n" +
                "      \"info\":{\n" +
                "        \"type\":\"text\",\n" +
                "        \"analyzer\":\"ik_max_word\"\n" +
                "      },\n" +
                "      \"email\":{\n" +
                "        \"type\":\"keyword\",\n" +
                "        \"index\":false\n" +
                "      },\n" +
                "      \"age\":{\n" +
                "        \"type\":\"integer\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("order");
        //库中的映射
        createIndexRequest.source(json, XContentType.JSON);
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @Test
    void exists() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("order");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void delete() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("order");
        restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }

}
