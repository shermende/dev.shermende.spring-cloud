package dev.shermende.authorization.configuration.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileUrlResource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.IOException;
import java.security.KeyPair;

@Slf4j
@Configuration
@Profile({"jwt"})
@RequiredArgsConstructor
public class JwtApplicationConfiguration {

    /**
     * Jwt authorization server key pair
     */
    // TODO: 19/2/21 брать из пропертей
    @Bean
    public KeyPair keyPair() throws IOException {
        return new KeyStoreKeyFactory(new FileUrlResource("./.examples/jwt.jks"), "password".toCharArray()).getKeyPair("jwt");
    }

}
