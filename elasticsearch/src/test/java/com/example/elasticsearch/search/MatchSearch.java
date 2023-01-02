package com.example.elasticsearch.search;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearch.entity.User;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * 字段分词检索
 */
public class MatchSearch extends Search{

    @Override
    public QueryBuilder queryBuilder() {
        return QueryBuilders.matchQuery("info", "勤恳");
    }

    @Override
    public void post(SearchSourceBuilder searchSourceBuilder) {
        //info字段高亮，不需要匹配搜索
        HighlightBuilder info = new HighlightBuilder().field("info").requireFieldMatch(false);
        searchSourceBuilder.highlighter(info);
    }

    @Override
    void sout(SearchResponse search) {
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits.getHits()) {
            User user = JSON.parseObject(hit.getSourceAsString(), User.class);
            user.setInfo(hit.getHighlightFields().get("info").getFragments()[0].string());
            System.out.println(user);
        }
    }
}
