package com.itnews.DAO;

import com.itnews.models.news.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author f.brishtan
 * @since 31.10.18.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentByNewsId(int newsId);

    Comment findById(int id);
}
