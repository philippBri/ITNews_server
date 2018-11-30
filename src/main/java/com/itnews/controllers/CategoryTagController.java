package com.itnews.controllers;

import com.itnews.models.news.Category;
import com.itnews.models.news.Tag;
import com.itnews.servers.CategoryService;
import com.itnews.servers.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author f.brishtan
 * @since 14.11.18.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CategoryTagController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/news/categories")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping(value = "/news/tags")
    public List<Tag> getTags() {
        return tagService.getAll();
    }
}
