package com.itnews.controllers;

import com.itnews.models.news.News;
import com.itnews.models.news.NewsDTO;
import com.itnews.servers.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author f.brishtan
 * @since 25.10.18.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/createNews", method = RequestMethod.POST)
    public News createOrUpdateNews(@RequestBody() NewsDTO news) {
        return newsService.createOrUpdate(news);
    }

//    @RequestMapping(value = "/news/{id}", method = RequestMethod.POST)
//    public NewsDTO getNews(@PathVariable("id") int id) {
//        return newsService.getNews(id);
//    }

    @GetMapping(value = "/newsList/{id}")
    public List<News> findAllNews(@PathVariable("id") int userId) {
        return newsService.getAllByUserId(userId);
    }

    @GetMapping(value = "/news")
    public List<News> findPublicNews() {
        return newsService.getAll();
    }

    @DeleteMapping(value = "newsList/{id}")
    public News deleteNews(@PathVariable("id") int id) {
        return newsService.delete(id);
    }

    @GetMapping(value = "/news/{id}")
    public News getNewsById(@PathVariable("id") int id) {
        return newsService.getNews(id);
    }

    @GetMapping(value = "/news/searchNewsByPhrase/{phrase}")
    public List<News> searchNews(@PathVariable("phrase") String searchString) {
        return newsService.findByWordOrPhrase(searchString);
    }

    @GetMapping(value = "/news/searchNewsByCategory/{id}")
    public List<News> getNewsByCategory(@PathVariable("id") int id) {
        return newsService.findByCategory(id);
    }

    @GetMapping(value = "/news/searchNewsByTag/{tag}")
    public List<News> getNewsByTag(@PathVariable("tag") String tag) {
        return newsService.findByTag(tag);
    }

//    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
//    public News updateNews(@RequestBody NewsDTO newsDTO) {
//        return newsService.update(newsDTO);
//    }

}
