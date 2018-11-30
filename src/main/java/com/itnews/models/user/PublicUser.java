package com.itnews.models.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author f.brishtan
 * @since 08.11.18.
 */
@Getter
@Setter
public class PublicUser {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String city;
    private String country;
    private String role;
}
