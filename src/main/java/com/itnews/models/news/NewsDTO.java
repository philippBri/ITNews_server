package com.itnews.models.news;

import com.itnews.models.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author f.brishtan
 * @since 25.10.18.
 */
@Getter
@Setter
public class NewsDTO {
    private int id;
    private String title;
    private String description;
    private int sectionId;
    private int tagId;
    private String text;
    private User user;
    private String creationDate;
    private Category category;
    private List<String> tags;
}
