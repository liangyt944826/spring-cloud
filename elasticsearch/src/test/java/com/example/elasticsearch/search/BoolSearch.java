package com.example.elasticsearch.search;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Arrays;

/**
 * 多个条件 filter must 为 && filter不参与计算得分  must_not
 */
public class BoolSearch extends Search{
    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("age","18"));
    }
}
