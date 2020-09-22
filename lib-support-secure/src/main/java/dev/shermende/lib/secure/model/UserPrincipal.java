package dev.shermende.lib.secure.model;

public interface UserPrincipal {

    Long id();

    String login();

    char[] token();

    UserPrincipal attachToken(char[] token);

}
