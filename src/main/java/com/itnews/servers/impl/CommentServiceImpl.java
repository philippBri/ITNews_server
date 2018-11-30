package com.itnews.servers.impl;

import com.itnews.DAO.CommentRepository;
import com.itnews.DAO.NewsRepository;
import com.itnews.DAO.UserDao;
import com.itnews.models.news.Comment;
import com.itnews.models.news.CommentDTO;
import com.itnews.models.news.News;
import com.itnews.models.user.User;
import com.itnews.servers.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author f.brishtan
 * @since 31.10.18.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserDao userDao;

    @Override
    public Comment createOrUpdate(CommentDTO commentDTO, int newsId) {
        Comment comment = new Comment();
        News news = newsRepository.findById(newsId);
        User author = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        comment.setDateCreation(new Date().toString());
        comment.setText(commentDTO.getText());
        if (news != null) {
            comment.setNews(news);
        }
        comment.setUser(author);
        return commentRepository.save(comment);
    }

    @Override
    public Comment delete(int id) {
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            commentRepository.delete(comment);
        }
        return comment;
    }

    @Override
    public Comment getComment(int id) {
        return null;
    }

    @Override
    public List<Comment> getAll(int newsId) {
        return commentRepository.getCommentByNewsId(newsId);
    }
}
