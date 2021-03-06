package dev.shermende.authorization.controller;

import dev.shermende.authorization.security.ExtendedUser;
import dev.shermende.authorization.service.impl.AppUserDetailsService;
import dev.shermende.authorization.util.TestingUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.active=testing,opaque")
class TokenEndpointOpaqueTest {
    private final EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AppUserDetailsService appUserDetailsService;

    @MockBean
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Test
    void oauthTokenNoAuthorization() throws Exception {
        // action
        this.mockMvc.perform(post("/oauth/token"))
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
        this.mockMvc.perform(post("/oauth/token")
            .header(HttpHeaders.AUTHORIZATION, basicToken))
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    void oauthTokenNoParams() throws Exception {
        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret)));

        // action
        this.mockMvc.perform(post("/oauth/token")
            .header(HttpHeaders.AUTHORIZATION, basicToken))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error_description").value("Missing grant type"))
        ;
    }

    @Test
    void oauthTokenBadCredentials() throws Exception {
        // constants
        final String grantType = "password";

        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);
        final BaseClientDetails client = TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret));

        // dao auth
        final Long id = easyRandom.nextLong();
        final String username = String.valueOf(easyRandom.nextLong());
        final String password = String.valueOf(easyRandom.nextLong());
        final List<GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;
        final ExtendedUser user = new ExtendedUser(id, username, password, authorities);

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(client);
        given(appUserDetailsService.loadUserByUsername(any()))
            .willReturn(user);

        // action
        this.mockMvc.perform(post("/oauth/token")
            .header(HttpHeaders.AUTHORIZATION, basicToken)
            .param("grant_type", grantType)
            .param("username", username)
            .param("password", password))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error_description").value("Bad credentials"))
        ;
    }

    @Test
    void oauthTokenOk() throws Exception {
        // constants
        final String grantType = "password";

        // basic auth
        final String clientId = String.valueOf(easyRandom.nextLong());
        final String clientSecret = String.valueOf(easyRandom.nextLong());
        final String basicToken = TestingUtil.basic(clientId, clientSecret);
        final BaseClientDetails client = TestingUtil.oauthApplicationGrantTypePassword(clientId, passwordEncoder.encode(clientSecret));

        // dao auth
        final Long id = easyRandom.nextLong();
        final String username = String.valueOf(easyRandom.nextLong());
        final String password = String.valueOf(easyRandom.nextLong());
        final List<GrantedAuthority> authorities = AuthorityUtils.NO_AUTHORITIES;
        final ExtendedUser user = new ExtendedUser(id, username, passwordEncoder.encode(password), authorities);

        // mocks
        given(jdbcClientDetailsService.loadClientByClientId(any()))
            .willReturn(client);
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
            .andExpect(jsonPath("$.refresh_token").exists())
        ;
    }

}