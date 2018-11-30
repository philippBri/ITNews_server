package com.itnews.servers.impl;

import com.itnews.DAO.TagRepository;
import com.itnews.models.news.Tag;
import com.itnews.models.news.TagDTO;
import com.itnews.servers.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author f.brishtan
 * @since 15.11.18.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag create(TagDTO tagDTO) {

        return null;
    }

    @Override
    public Tag delete(int id) {
        return null;
    }

    @Override
    public Tag getTagById(int id) {
        return null;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findTop20Tags();
    }
}
