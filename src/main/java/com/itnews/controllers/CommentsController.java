package com.itnews.controllers;

import com.itnews.models.news.Comment;
import com.itnews.models.news.CommentDTO;
import com.itnews.servers.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author f.brishtan
 * @since 31.10.18.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/createComment/{id}", method = RequestMethod.POST)
    public Comment createOrUpdateComment(@RequestBody() CommentDTO commentDTO, @PathVariable("id") int newsId) {
        return commentService.createOrUpdate(commentDTO, newsId);
    }

    @GetMapping(value = "/commentsList/{id}")
    public List<Comment> getComments(@PathVariable("id") int id) {
        return commentService.getAll(id);
    }

    @RequestMapping(value = "/deleteComment/{id}", method = RequestMethod.DELETE)
    public Comment deleteComment(@PathVariable("id") int id) {
        return commentService.delete(id);
    }
}
