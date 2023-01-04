package dev.shermende.authorization.configuration.jwt;

import dev.shermende.authorization.security.jwt.JwtDefaultAccessTokenConverter;
import dev.shermende.authorization.security.jwt.JwtDefaultUserAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * Jwt authorization server beans
 */
@Slf4j
@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
public class JwtAuthorizationServerBeansConfiguration {

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;
    @Qualifier("appUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Qualifier("objectPostProcessor")
    private final ObjectPostProcessor<Object> objectObjectPostProcessor;

    /**
     * Jwt authorization server authentication manager
     */
    @Bean
    public AuthenticationManager jwtAuthorizationServerAuthenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(objectObjectPostProcessor)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
            .and().build();
    }

    /**
     * Jwt authorization server token store
     */
    @Bean
    public TokenStore jwtAuthorizationServerTokenStore(
        JwtAccessTokenConverter jwtAccessTokenConverter
    ) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * Jwt authorization server access-token converter
     */
    @Bean
    public JwtAccessTokenConverter jwtAuthorizationServerTokenConverter(
        KeyPair keyPair
    ) {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        converter.setAccessTokenConverter(new JwtDefaultAccessTokenConverter("env-authorization", new JwtDefaultUserAuthenticationConverter()));
        return converter;
    }

    /**
     * Jwt authorization server key pair
     */
    @Bean
    public KeyPair keyPair(
        @Value("${security.oauth2.authorization.jwt.private-key}") String privateKeyFile
    ) throws NoSuchAlgorithmException, InvalidKeySpecException, MalformedURLException {
        val keyFactory = KeyFactory.getInstance("RSA");
        val privateKey = getPrivateKey(privateKeyFile, keyFactory);
        val publicKey = getPublicKey(privateKey, keyFactory);
        return new KeyPair(publicKey, privateKey);
    }

    private PrivateKey getPrivateKey(String privateKeyFile, KeyFactory keyFactory) throws InvalidKeySpecException {
        val privateBytes = Base64.getDecoder().decode(privateKeyFile.replaceAll(" ", "").replace("\n", ""));
        val privateKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    private PublicKey getPublicKey(PrivateKey privateKey, KeyFactory keyFactory) throws InvalidKeySpecException {
        val privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        val publicKeySpec = new RSAPublicKeySpec(privateKeySpec.getModulus(), BigInteger.valueOf(65537));
        return keyFactory.generatePublic(publicKeySpec);
    }

}
