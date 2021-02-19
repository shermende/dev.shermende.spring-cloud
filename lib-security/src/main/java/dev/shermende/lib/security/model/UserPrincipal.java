package dev.shermende.lib.security.model;

/**
 * Common interface of SecurityPrincipal
 */
public interface UserPrincipal {

    Long id();

    String login();

    char[] token();

    UserPrincipal attachToken(char[] token);

}
