package com.itnews.models.news;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * @author f.brishtan
 * @since 31.10.18.
 */
@Getter
@Setter
public class CommentDTO {
    private int id;
    private String author;
    private String dateCreation;
    private int newsId;
    private int likes;
    private String text;
    private News news;
}
