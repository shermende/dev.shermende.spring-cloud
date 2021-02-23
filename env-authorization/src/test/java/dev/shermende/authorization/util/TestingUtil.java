package dev.shermende.authorization.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Base64;

@UtilityClass
public class TestingUtil {

    public String basic(
        String username,
        String password
    ) {
        final String format = String.format("%s:%s", username, password);
        return String.format("Basic %s", Base64.getEncoder().encodeToString(format.getBytes()));
    }

    public BaseClientDetails oauthApplicationGrantTypePassword(
        String clientId,
        String clientSecret
    ) {
        final BaseClientDetails oauthApplication =
            new BaseClientDetails(clientId, null, "testing:testing", "password,refresh_token", null);
        oauthApplication.setClientSecret(clientSecret);
        return oauthApplication;
    }

}