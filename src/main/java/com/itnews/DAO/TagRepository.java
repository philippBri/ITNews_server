package com.itnews.DAO;

import com.itnews.models.news.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author f.brishtan
 * @since 15.11.18.
 */
public interface TagRepository extends CrudRepository<Tag, Integer> {
    List<Tag> findTagsById(int newsId);

    Tag findById(int id);

    Tag findByName(String name);

    @Query(value = "select t.id, t.name from tag t " +
            "join news_tag nt on t.id = nt.tag_id " +
            "group by t.id " +
            "order by count(nt.tag_id) desc " +
            "limit 20", nativeQuery = true)
    List<Tag> findTop20Tags();
}
