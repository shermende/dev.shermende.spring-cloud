package dev.shermende.lib.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
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