package com.itnews.DAO;

import com.itnews.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author f.brishtan
 * @since 17.10.18.
 */
public interface UserDao extends CrudRepository<User,Long> {
    @Query("select u from User u where u.isDeleted = false and u.username = ?1")
    User findByUsername(String username);
}
