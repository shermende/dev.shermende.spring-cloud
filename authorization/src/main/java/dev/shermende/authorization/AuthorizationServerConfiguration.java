package dev.shermende.authorization;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
@EnableConfigurationProperties({
    AuthorizationServerConfiguration.KeyProperties.class,
    AuthorizationServerConfiguration.ClientProperties.class,
})
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final KeyPair keyPair;
    private final PasswordEncoder passwordEncoder;
    private final ClientProperties clientProperties;
    private final AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(clientProperties.getClient())
            .secret(passwordEncoder.encode(clientProperties.getSecret()))
            .authorizedGrantTypes(clientProperties.getTypes())
            .scopes(clientProperties.getScopes())
        ;
    }

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

    @Configuration
    @RequiredArgsConstructor
    public static class JksConfiguration {
        private final KeyProperties properties;

        @Bean
        public KeyPair keyPair() throws InvalidKeySpecException, NoSuchAlgorithmException {
            final KeyFactory factory = KeyFactory.getInstance("RSA");
            final RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(new BigInteger(properties.getModulus()), new BigInteger(properties.getExponent()));
            final RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(new BigInteger(properties.getModulus()), new BigInteger(properties.getPrivateExponent()));
            return new KeyPair(factory.generatePublic(publicSpec), factory.generatePrivate(privateSpec));
        }
    }

    @Data
    @ConfigurationProperties("oauth2.client")
    public static class ClientProperties {
        private String client = "client";
        private String secret = "secret";
        private String[] types = new String[]{"password"};
        private String[] scopes = new String[]{"oauth2:any"};
    }

    @Data
    @ConfigurationProperties("oauth2.key")
    public static class KeyProperties {
        private String privateExponent = "3851612021791312596791631935569878540203393691253311342052463788814433805390794604753109719790052408607029530149004451377846406736413270923596916756321977922303381344613407820854322190592787335193581632323728135479679928871596911841005827348430783250026013354350760878678723915119966019947072651782000702927096735228356171563532131162414366310012554312756036441054404004920678199077822575051043273088621405687950081861819700809912238863867947415641838115425624808671834312114785499017269379478439158796130804789241476050832773822038351367878951389438751088021113551495469440016698505614123035099067172660197922333993";
        private String modulus = "18044398961479537755088511127417480155072543594514852056908450877656126120801808993616738273349107491806340290040410660515399239279742407357192875363433659810851147557504389760192273458065587503508596714389889971758652047927503525007076910925306186421971180013159326306810174367375596043267660331677530921991343349336096643043840224352451615452251387611820750171352353189973315443889352557807329336576421211370350554195530374360110583327093711721857129170040527236951522127488980970085401773781530555922385755722534685479501240842392531455355164896023070459024737908929308707435474197069199421373363801477026083786683";
        private String exponent = "65537";
    }

}
