package dev.shermende.lib.security.jwt;

import dev.shermende.lib.security.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class JwtAccessTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String CLIENT_ID = "client_id";
    private static final String SCOPE = "scope";

    private final Converter<Jwt, UserPrincipal> principalConverter;

    /**
     * Force use principal as {@link UserPrincipal}
     */
    @NotNull
    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt jwt) {
        return new OAuth2Authentication(
            getOauth2Request(getParameters(jwt), getAuthorities(jwt), getScopes(jwt), getResourceIds(jwt), jwt),
            new UsernamePasswordAuthenticationToken(principalConverter.convert(jwt), null, AuthorityUtils.NO_AUTHORITIES)
        );
    }

    @NotNull
    private Map<String, String> getParameters(
        @NotNull Jwt jwt
    ) {
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CLIENT_ID, jwt.getClaim(CLIENT_ID));
        return parameters;
    }

    @NotNull
    private List<GrantedAuthority> getAuthorities(
        @NotNull Jwt jwt
    ) {
        return Collections.emptyList();
    }

    @NotNull
    private Set<String> getScopes(
        @NotNull Jwt jwt
    ) {
        return new HashSet<>(Optional.ofNullable(jwt.getClaimAsStringList(SCOPE)).orElseGet(Collections::emptyList));
    }

    @NotNull
    private Set<String> getResourceIds(
        @NotNull Jwt jwt
    ) {
        return Collections.emptySet();
    }

    @NotNull
    private OAuth2Request getOauth2Request(
        @NotNull Map<String, String> parameters,
        @NotNull List<GrantedAuthority> authorities,
        @NotNull Set<String> scopes,
        @NotNull Set<String> resourceIds,
        @NotNull Jwt jwt
    ) {
        return new OAuth2Request(parameters, jwt.getClaim(CLIENT_ID), authorities,
            true, scopes, resourceIds, null, null, null);
    }

}