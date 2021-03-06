package dev.shermende.authorization.controller;

import dev.shermende.authorization.security.ExtendedUser;
import dev.shermende.authorization.util.TestingUtil;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=testing,opaque")
class CheckTokenOpaqueTest {
    private final EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JdbcClientDetailsService jdbcClientDetailsService;

    @MockBean
    @Qualifier("opaqueAuthorizationServerTokenStore")
    private TokenStore tokenStore;

    @Test
    void oauthTokenNoAuthorization() throws Exception {
        // action
        this.mockMvc.perform(post("/oauth/check_token"))
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    void oauthTokenWrongAuthorization() throws Exception {
        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);

        // action
        this.mockMvc.perform(post("/oauth/check_token")
            .header(HttpHeaders.AUTHORIZATION, basicToken))
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    void oauthTokenNoToken() throws Exception {
        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);
        final BaseClientDetails client = TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret));

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(client);

        // action
        this.mockMvc.perform(post("/oauth/check_token")
            .header(HttpHeaders.AUTHORIZATION, basicToken))
            .andDo(print())
            .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void oauthTokenWrongToken() throws Exception {
        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);
        final BaseClientDetails client = TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret));

        // token
        final String token = String.valueOf(easyRandom.nextLong());

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(client);

        // action
        this.mockMvc.perform(post("/oauth/check_token")
            .header(HttpHeaders.AUTHORIZATION, basicToken)
            .param("token", token))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error_description").value("Token was not recognised"))
        ;
    }

    @Test
    void oauthToken200() throws Exception {
        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);
        final BaseClientDetails client = TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret));

        // token
        final String token = String.valueOf(easyRandom.nextLong());
        final DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(String.valueOf(easyRandom.nextLong()));

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

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(client);
        given(tokenStore.readAccessToken(anyString()))
            .willReturn(accessToken);
        given(tokenStore.readAuthentication(any(OAuth2AccessToken.class)))
            .willReturn(authentication);

        // action
        this.mockMvc.perform(post("/oauth/check_token")
            .header(HttpHeaders.AUTHORIZATION, basicToken)
            .param("token", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.active").exists())
            .andExpect(jsonPath("$.user_name").exists())
            .andExpect(jsonPath("$.client_id").exists())
        ;
    }

}