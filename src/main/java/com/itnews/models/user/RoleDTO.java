package com.itnews.models.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class RoleDTO {
    private int id;
    private String name;
    private Collection<User> users;
}
