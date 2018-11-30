package com.itnews.models.news;

import com.itnews.DAO.CustomAnalyzer;
import com.itnews.models.user.User;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author f.brishtan
 * @since 25.10.18.
 */
@Entity
@Indexed //it tells Hibernate Search that this entity shall be indexed
@Table(name = "news")
@Getter
@Setter
public class News {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Analyzer(impl = CustomAnalyzer.class)
    @Field(index= Index.YES)
    private String title;
    @Column(columnDefinition = "text")
    @Analyzer(impl = CustomAnalyzer.class)
    @Field(index= Index.YES)
    private String description;
    @Column(columnDefinition = "mediumtext")
    @Analyzer(impl = CustomAnalyzer.class)
    @Field(index= Index.YES)
    private String text;

    @Column
    private String creationDate;

    @ManyToOne()
    private User user;

    @ManyToOne()
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "news_category",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "news_tag",
            joinColumns = {@JoinColumn(name = "news_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    List<Tag> tags = new ArrayList<>();
}
