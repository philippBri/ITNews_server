package com.itnews.DAO;

import com.itnews.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    void delete(User user);

    @Query("select u from User u where u.isDeleted = false")
    List<User> findAll();

    User findById(int id);

    User save(User user);
}
