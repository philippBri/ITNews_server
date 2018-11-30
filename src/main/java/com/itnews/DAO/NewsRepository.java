package com.itnews.DAO;

import com.itnews.models.news.Category;
import com.itnews.models.news.News;
import com.itnews.models.news.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author f.brishtan
 * @since 25.10.18.
 */
public interface NewsRepository extends CrudRepository<News, Integer> {
    News findById(int id);
    List<News> getAllByUserId(int userId);
    List<News> findAllByCategory(Category category);
    List<News> findAllByTagsIsContaining(Tag tag);
}
