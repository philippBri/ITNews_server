package com.itnews.models.news;

import com.itnews.DAO.CustomAnalyzer;
import com.itnews.models.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author f.brishtan
 * @since 31.10.18.
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String dateCreation;
    @Column(columnDefinition = "text")
    @Analyzer(impl = CustomAnalyzer.class)
    @Field(index= Index.YES)
    private String text;
    @ManyToOne()
    private News news;
    @OneToOne
    private User user;

}
