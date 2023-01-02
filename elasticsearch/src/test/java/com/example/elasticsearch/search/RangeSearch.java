package com.example.elasticsearch.search;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class RangeSearch extends Search{
    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.rangeQuery("age").gte(10).lte(30);
    }
}
