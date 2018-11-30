package com.itnews.servers;

import com.itnews.models.news.Tag;
import com.itnews.models.news.TagDTO;

import java.util.List;

/**
 * @author f.brishtan
 * @since 15.11.18.
 */
public interface TagService {

    Tag create(TagDTO tagDTO);

    Tag delete(int id);

    Tag getTagById(int id);

    Tag getTagByName(String name);

    List<Tag> getAll();
}
