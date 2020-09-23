package dev.shermende.lib.security.model;

public interface UserPrincipal {

    Long id();

    String login();

    char[] token();

    UserPrincipal attachToken(char[] token);

}
