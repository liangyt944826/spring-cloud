package com.example.elasticsearch.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * 全文检索
 */
public class MatchAllSearch extends Search{

    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.matchAllQuery();
    }


}
