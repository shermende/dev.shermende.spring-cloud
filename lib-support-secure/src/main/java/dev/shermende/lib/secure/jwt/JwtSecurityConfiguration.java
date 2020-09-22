package dev.shermende.lib.secure.jwt;

import dev.shermende.lib.secure.util.RSA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;

@Configuration

public class JwtSecurityConfiguration {

    /**
     * load public key
     */
    @Bean
    public RSAPublicKey rsaPublicKey(
        @Value("classpath:jwt/public.pem") Resource publicKeyFile
    ) throws GeneralSecurityException, IOException {
        return RSA.getPublicKey(publicKeyFile.getFile().getAbsolutePath());
    }

    /**
     * jwt decoder
     */
    @Bean
    public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    /**
     * Converter
     * JWT -> {@link org.springframework.security.core.Authentication}
     */
    @Bean
    public JwtAuthenticationSupportConverter appJwtAuthenticationConverter() {
        return new JwtAuthenticationSupportConverter(new JwtUserPrincipalSupportConverter(), new JwtAuthenticationConverter());
    }

}