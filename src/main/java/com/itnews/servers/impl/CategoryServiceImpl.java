package com.itnews.servers.impl;

import com.itnews.DAO.CategoryRepository;
import com.itnews.models.news.Category;
import com.itnews.models.news.CategoryDTO;
import com.itnews.servers.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author f.brishtan
 * @since 14.11.18.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(CategoryDTO categoryDTO) {
        Category category = new Category();
        if (categoryRepository.findById(categoryDTO.getId()) != null) {
            category.setId(categoryDTO.getId());
        }
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(int id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            categoryRepository.delete(category);
        }
        return category;
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
