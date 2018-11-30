package com.itnews.models.user;

import com.itnews.models.news.News;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author f.brishtan
 * @since 17.10.18.
 */
@Getter
@Setter
public class UserDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String city;
    private String country;
    private String role;
    private int id;
    private boolean isDeleted;
    private boolean isBlocked;
    private Collection<Role> roles;
}
