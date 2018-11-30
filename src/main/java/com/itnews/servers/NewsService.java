package com.itnews.servers;

import com.itnews.models.news.News;
import com.itnews.models.news.NewsDTO;

import java.util.List;

/**
 * @author f.brishtan
 * @since 25.10.18.
 */
public interface NewsService {
    News createOrUpdate(NewsDTO news);
    News update(NewsDTO news);
    News delete(int id);
    News getNews(int id);
    List<News> getAll();
    List<News> getAllByUserId(int id);
    List<News> findByWordOrPhrase(String search);
    List<News> findByCategory(int id);
    List<News> findByTag(String tag);
}
