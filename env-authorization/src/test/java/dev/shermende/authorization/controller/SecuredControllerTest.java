package dev.shermende.authorization.controller;

import dev.shermende.lib.security.model.impl.PrincipalUser;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.profiles.include=jwt")
class SecuredControllerTest {
    private final EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("jwtResourceServerAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Test
    void scopesNone200() throws Exception {
        given(authenticationManager.authenticate(any())).willReturn(
            new OAuth2Authentication(
                new OAuth2Request(new HashMap<>(), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES,
                    true, new HashSet<>(Collections.singletonList("oauth2:none")), Collections.emptySet(), null, null, null),
                new UsernamePasswordAuthenticationToken(easyRandom.nextObject(PrincipalUser.class), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES)
            ));

        this.mockMvc.perform(get("/secured/oauth2-none")
            .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", easyRandom.nextLong())))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void authoritiesNone200() throws Exception {
        given(authenticationManager.authenticate(any())).willReturn(
            new OAuth2Authentication(
                new OAuth2Request(new HashMap<>(), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES,
                    true, Collections.emptySet(), Collections.emptySet(), null, null, null),
                new UsernamePasswordAuthenticationToken(easyRandom.nextObject(PrincipalUser.class), String.valueOf(easyRandom.nextLong()), Collections.singleton(new SimpleGrantedAuthority("ROLE_NONE")))
            ));

        this.mockMvc.perform(get("/secured/authority-none")
            .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", easyRandom.nextLong())))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void scopesNone401() throws Exception {
        this.mockMvc.perform(post("/secured/oauth2-none"))
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }

    @Test
    void authoritiesNone401() throws Exception {
        this.mockMvc.perform(post("/secured/authority-none"))
            .andDo(print())
            .andExpect(status().isUnauthorized());
    }

    @Test
    void scopesNone403() throws Exception {
        given(authenticationManager.authenticate(any())).willReturn(
            new OAuth2Authentication(
                new OAuth2Request(new HashMap<>(), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES,
                    true, Collections.emptySet(), Collections.emptySet(), null, null, null),
                new UsernamePasswordAuthenticationToken(String.valueOf(easyRandom.nextLong()), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES)
            ));

        this.mockMvc.perform(get("/secured/oauth2-none")
            .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", easyRandom.nextLong())))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

    @Test
    void authoritiesNone403() throws Exception {
        given(authenticationManager.authenticate(any())).willReturn(
            new OAuth2Authentication(
                new OAuth2Request(new HashMap<>(), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES,
                    true, Collections.emptySet(), Collections.emptySet(), null, null, null),
                new UsernamePasswordAuthenticationToken(String.valueOf(easyRandom.nextLong()), String.valueOf(easyRandom.nextLong()), AuthorityUtils.NO_AUTHORITIES)
            ));

        this.mockMvc.perform(get("/secured/authority-none")
            .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", easyRandom.nextLong())))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

}