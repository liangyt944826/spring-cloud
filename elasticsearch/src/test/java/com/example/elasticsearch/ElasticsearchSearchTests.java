package com.example.elasticsearch;

import com.example.elasticsearch.search.*;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ElasticsearchSearchTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 全文检索
     * @throws IOException
     */
    @Test
    void matchAll() throws IOException {
        Search search = new MatchAllSearch();
        search.search(restHighLevelClient);
    }

    /**
     * 单字段全文检索
     * @throws IOException
     */
    @Test
    void match() throws IOException {
        Search search = new MatchSearch();
        search.search(restHighLevelClient);
    }

    /**
     * 单字段多值全文检索
     * @throws IOException
     */
    @Test
    void multiMatch() throws IOException {
        Search search = new MultiMatchSearch();
        search.search(restHighLevelClient);
    }

    /**
     * 多id全文检索
     * @throws IOException
     */
    @Test
    void ids() throws IOException {
        Search search = new IdsSearch();
        search.search(restHighLevelClient);
    }

    /**
     * 单字段精确匹配
     * @throws IOException
     */
    @Test
    void term() throws IOException {
        Search search = new TermSearch();
        search.search(restHighLevelClient);
    }

    /**
     * 范围匹配
     * @throws IOException
     */
    @Test
    void range() throws IOException {
        Search search = new RangeSearch();
        search.search(restHighLevelClient);
    }

    /**
     * 聚合
     * @throws IOException
     */
    @Test
    void aggs() throws IOException {
        SearchRequest searchRequest = new SearchRequest("order");
        searchRequest.source().size(0);
                                                                            //聚合的名字
        searchRequest.source().aggregation(AggregationBuilders.terms("aggr")
                //聚合的字段 不可以为text格式
                .field("age"));
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        Terms terms = search.getAggregations().get("aggr");
        for (Terms.Bucket bucket : terms.getBuckets()) {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        }
    }

}
