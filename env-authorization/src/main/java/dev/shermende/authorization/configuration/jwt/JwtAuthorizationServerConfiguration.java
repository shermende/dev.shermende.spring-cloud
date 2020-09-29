package dev.shermende.authorization.configuration.jwt;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import dev.shermende.lib.security.configuration.jwt.JwtProperties;
import dev.shermende.lib.security.util.RSA;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;

@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
@EnableAuthorizationServer
public class JwtAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final KeyPair keyPair;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        converter.setAccessTokenConverter(new JwtDefaultAccessTokenConverter());
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /**
     * public key endpoint
     */
    @RestController
    @Profile({"jwt"})
    @RequiredArgsConstructor
    public static class WellKnownConfiguration {
        private final KeyPair keyPair;

        @GetMapping("/.well-known/jwks.json")
        public Map<String, Object> getKey() {
            final RSAPublicKey publicKey = (RSAPublicKey) this.keyPair.getPublic();
            final RSAKey key = new RSAKey.Builder(publicKey).build();
            return new JWKSet(key).toJSONObject();
        }
    }

    /**
     * RSA key-pair for JWT
     */
    @Configuration
    @Profile({"jwt"})
    @RequiredArgsConstructor
    public static class JwtConfiguration {

        /**
         * bean of JWT properties
         */
        @Bean
        @Validated
        @ConfigurationProperties("x-jwt")
        public JwtProperties jwtProperties() {
            return new JwtProperties();
        }

        /**
         * RSA key-pair for JWT
         */
        @Bean
        public KeyPair keyPair() throws GeneralSecurityException, IOException {
            final KeyFactory factory = KeyFactory.getInstance("RSA");
            final RSAPublicKey publicKey = RSA.getPublicKey(jwtProperties().getPublicKeyPath());
            final RSAPrivateKey privateKey = RSA.getPrivateKey(jwtProperties().getPrivateKeyPath());
            final RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(publicKey.getModulus(), publicKey.getPublicExponent());
            final RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(privateKey.getModulus(), privateKey.getPrivateExponent());
            return new KeyPair(factory.generatePublic(publicSpec), factory.generatePrivate(privateSpec));
        }

    }

}
