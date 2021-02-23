package dev.shermende.authorization.controller;

import dev.shermende.authorization.security.ExtendedUser;
import dev.shermende.authorization.service.impl.AppUserDetailsService;
import dev.shermende.authorization.util.TestingUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=testing,jwt")
class TokenEndpointRefreshTokenJwtTest {
    private final EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    @Qualifier("jwtAuthorizationServerTokenStore")
    private TokenStore tokenStore;

    @MockBean
    private AppUserDetailsService appUserDetailsService;

    @MockBean
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Test
    void oauthTokenBadCredentials() throws Exception {
        // constants
        final String grantType = "refresh_token";

        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);
        final BaseClientDetails client = TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret));

        // user
        final Long id = easyRandom.nextLong();
        final String username = String.valueOf(easyRandom.nextLong());
        final String password = String.valueOf(easyRandom.nextLong());
        final List<GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;
        final ExtendedUser user = new ExtendedUser(id, username, password, authorities);
        final OAuth2Authentication authentication = new OAuth2Authentication(
            new OAuth2Request(new HashMap<>(), client.getClientId(), client.getAuthorities(), true, client.getScope(), client.getResourceIds(), null, null, null),
            new UsernamePasswordAuthenticationToken(user, String.valueOf(easyRandom.nextLong()), authorities)
        );

        // refresh token
        final DefaultOAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(String.valueOf(easyRandom.nextLong()));

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(client);
        given(tokenStore.readRefreshToken(any()))
            .willReturn(refreshToken);
        given(tokenStore.readAuthenticationForRefreshToken(any(OAuth2RefreshToken.class)))
            .willReturn(authentication);
        given(appUserDetailsService.loadUserByUsername(any()))
            .willReturn(user);

        // action
        this.mockMvc.perform(post("/oauth/token")
            .header(HttpHeaders.AUTHORIZATION, basicToken)
            .param("grant_type", grantType)
            .param("username", username)
            .param("password", password))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.access_token").exists())
        ;
    }

}