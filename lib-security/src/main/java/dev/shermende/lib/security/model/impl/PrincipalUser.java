package dev.shermende.lib.security.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shermende.lib.security.model.UserPrincipal;
import lombok.Builder;
import lombok.Data;

/**
 * Default implementation of {@link UserPrincipal}
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrincipalUser implements UserPrincipal {

    private Long id;

    private String email;

    private char[] token;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public String login() {
        return getEmail();
    }

    @Override
    public char[] token() {
        return getToken();
    }

    @Override
    public UserPrincipal attachToken(char[] token) {
        return setToken(token);
    }

}