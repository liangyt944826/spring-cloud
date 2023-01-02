package com.example.elasticsearch.search;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearch.entity.User;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

public abstract class Search {

    public void search(RestHighLevelClient restHighLevelClient) throws IOException {
        SearchRequest searchRequest = new SearchRequest("order");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);

        post(searchSourceBuilder);

        QueryBuilder queryBuilder = queryBuilder();

        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);

        sout(restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT));
    }

    public void post(SearchSourceBuilder searchSourceBuilder){

    }

    public abstract QueryBuilder queryBuilder();

    void sout(SearchResponse search){
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits.getHits()) {
            User user = JSON.parseObject(hit.getSourceAsString(), User.class);
            System.out.println(user);
        }
    }
}
