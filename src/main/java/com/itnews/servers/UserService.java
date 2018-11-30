package com.itnews.servers;

import com.itnews.models.user.PublicUser;
import com.itnews.models.user.User;
import com.itnews.models.user.UserDTO;

import java.util.List;

public interface UserService {

    User create(UserDTO user);

    User delete(int id);

    List<User> findAll();

    User findById(int id);

    PublicUser findByIdPublicUser(int id);

    User findOne(String username);

    User update(UserDTO user);

    User blockUser(int id);
}
