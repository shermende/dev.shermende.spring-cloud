package dev.shermende.authorization.configuration.jwt;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import dev.shermende.lib.secure.util.RSA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
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

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        converter.setAccessTokenConverter(new JwtDefaultAccessTokenConverter());
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * provide public key
     */
    @RestController
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
     * rsa key
     */
    @Configuration
    @RequiredArgsConstructor
    public static class JwtConfiguration {

        @Value("classpath:jwt/private.key")
        private Resource privateKeyFile;
        @Value("classpath:jwt/public.pem")
        private Resource publicKeyFile;

        @Bean
        public KeyPair keyPair() throws GeneralSecurityException, IOException {
            final KeyFactory factory = KeyFactory.getInstance("RSA");
            final RSAPublicKey publicKey = RSA.getPublicKey(publicKeyFile.getFile().getAbsolutePath());
            final RSAPrivateKey privateKey = RSA.getPrivateKey(privateKeyFile.getFile().getAbsolutePath());
            final RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(publicKey.getModulus(), publicKey.getPublicExponent());
            final RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(privateKey.getModulus(), privateKey.getPrivateExponent());
            return new KeyPair(factory.generatePublic(publicSpec), factory.generatePrivate(privateSpec));
        }

    }

}
