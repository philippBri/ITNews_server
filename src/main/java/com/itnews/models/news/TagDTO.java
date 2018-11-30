package com.itnews.models.news;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author f.brishtan
 * @since 15.11.18.
 */
@Getter
@Setter
public class TagDTO {
    private int id;
    private String name;
    private List<News> news;
}
