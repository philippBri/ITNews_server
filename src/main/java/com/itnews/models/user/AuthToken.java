package com.itnews.models.user;

/**
 * @author f.brishtan
 * @since 18.10.18.
 */
public class AuthToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
