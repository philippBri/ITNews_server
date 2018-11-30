package com.itnews.DAO;

import com.itnews.models.news.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author f.brishtan
 * @since 14.11.18.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getCategoryById(int newsId);

    Category findById(int id);

    Category findByName(String name);
}
