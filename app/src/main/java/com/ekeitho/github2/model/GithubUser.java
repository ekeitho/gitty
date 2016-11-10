package com.ekeitho.github2.model;

public class GithubUser {
    public final String login;
    public final int id;
    public final String location;

    public GithubUser(String login, String location, int id) {
        this.login = login;
        this.location = location;
        this.id = id;
    }
}
