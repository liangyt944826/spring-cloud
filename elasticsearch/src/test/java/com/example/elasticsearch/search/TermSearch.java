package com.example.elasticsearch.search;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class TermSearch extends Search{
    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.termQuery("age","22");
    }
}
