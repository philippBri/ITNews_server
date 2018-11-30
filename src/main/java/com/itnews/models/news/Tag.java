package com.itnews.models.news;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author f.brishtan
 * @since 15.11.18.
 */
@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
//    @ManyToMany(mappedBy = "tags")
//    private List<News> news = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }
}
