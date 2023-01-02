package com.example.elasticsearch.search;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * id查询
 */
public class IdsSearch extends Search{
    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.idsQuery().addIds("1","2");
    }
}
