package com.itnews.servers.impl;

import com.itnews.DAO.CategoryRepository;
import com.itnews.DAO.NewsRepository;
import com.itnews.DAO.NewsSearchDao;
import com.itnews.DAO.TagRepository;
import com.itnews.DAO.UserDao;
import com.itnews.models.news.Category;
import com.itnews.models.news.News;
import com.itnews.models.news.NewsDTO;
import com.itnews.models.news.Tag;
import com.itnews.models.user.User;
import com.itnews.servers.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author f.brishtan
 * @since 25.10.18.
 */
@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NewsSearchDao newsSearchDao;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public News createOrUpdate(NewsDTO news) {
        News newNews = new News();
        User author = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (newsRepository.existsById(news.getId())) {
            newNews = newsRepository.findById(news.getId());
            newNews.setId(news.getId());
        }
        newNews.setTitle(news.getTitle());
        newNews.setDescription(news.getDescription());
        newNews.setText(news.getText());
        newNews.setCreationDate(new Date().toString());
        newNews.setUser(author);
        newNews.setCategory(news.getCategory());

        List<Tag> tags = new ArrayList<>();

        for (String tag : news.getTags()) {
            Tag exTag = tagRepository.findByName(tag);
            if (exTag != null) {
                tags.add(exTag);
            } else {
                tags.add(new Tag(tag));
            }
        }

        newNews.setTags(tags);


        return newsRepository.save(newNews);
    }

    @Override
    public News update(NewsDTO news) {
        return null;
    }

    @Override
    public News delete(int id) {
        News news = newsRepository.findById(id);
        if (news != null) {
            newsRepository.delete(news);
        }
        return news;
    }

    @Override
    public News getNews(int id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> getAll() {
        return (List<News>) newsRepository.findAll();
    }

    @Override
    public List<News> getAllByUserId(int userID) {
        return newsRepository.getAllByUserId(userID);
    }

    @Override
    public List<News> findByWordOrPhrase(String search) {
        List<News> news;
        if (search.contains(" ")) {
            news = newsSearchDao.searchNewsByPhrase(search);
        } else {
            news = newsSearchDao.searchNewsByWildcardQuery(search);
        }
        if (news.size() == 0) {
            news = newsSearchDao.searchNewsByKeywordQuery(search);
        }
        return news;
    }

    @Override
    public List<News> findByCategory(int id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            return newsRepository.findAllByCategory(category);
        }
        return new ArrayList<>();
    }

    @Override
    public List<News> findByTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName);
        if (tag != null) {
            return newsRepository.findAllByTagsIsContaining(tag);
        }
        return new ArrayList<>();
    }
}
