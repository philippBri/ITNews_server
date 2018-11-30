package com.itnews.servers;

import com.itnews.models.news.Comment;
import com.itnews.models.news.CommentDTO;
import com.itnews.models.news.News;
import com.itnews.models.news.NewsDTO;

import java.util.List;

/**
 * @author f.brishtan
 * @since 31.10.18.
 */
public interface CommentService {
    Comment createOrUpdate(CommentDTO commentDTO, int newsId);
    Comment delete(int id);
    Comment getComment(int id);
    List<Comment> getAll(int newsId);
}
