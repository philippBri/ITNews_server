package com.itnews.DAO;

import com.itnews.models.news.News;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author f.brishtan
 * @since 19.11.18.
 */

@Repository
public class NewsSearchDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<News> searchNewsByKeywordQuery(String text) {
        Query keywordQuery = getQBuilder()
                .keyword()
                .onFields("title", "description", "text")
                .matching(text)
                .createQuery();

        List<News> results = getJpaQuery(keywordQuery).getResultList();

        return results;
    }

    public List<News> searchNewsByWildcardQuery(String text) {
        Query wildcardQuery = getQBuilder()
                .keyword()
                .wildcard()
                .onFields("title", "description", "text")
                .matching(text + "*")
                .createQuery();

        List<News> results = getJpaQuery(wildcardQuery).getResultList();

        return results;
    }

    public List<News> searchNewsByPhrase(String text) {
        Query wildcardQuery = getQBuilder()
                .phrase()
                .onField("title")
                .andField("description")
                .andField("text")
                .sentence(text)
                .createQuery();

        List<News> results = getJpaQuery(wildcardQuery).getResultList();

        return results;
    }


    private FullTextQuery getJpaQuery(Query luceneQuery) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.createFullTextQuery(luceneQuery, News.class);
    }

    private QueryBuilder getQBuilder() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(News.class)
                .get();

    }
}
