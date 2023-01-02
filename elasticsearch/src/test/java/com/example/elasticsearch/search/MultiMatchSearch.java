package com.example.elasticsearch.search;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * 多字段分词检索
 */
public class MultiMatchSearch extends Search{

    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.multiMatchQuery("勤恳", "info", "email");
    }
}
