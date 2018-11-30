package com.itnews.servers;

import com.itnews.models.news.Category;
import com.itnews.models.news.CategoryDTO;

import java.util.List;

/**
 * @author f.brishtan
 * @since 14.11.18.
 */
public interface CategoryService {
    Category create(CategoryDTO category);

    Category delete(int id);

    Category getCategoryById(int id);

    Category getCategoryByName(String name);

    List<Category> getAll();
}
