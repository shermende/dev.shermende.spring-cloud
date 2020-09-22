package dev.shermende.lib.secure.configuration.ouath;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OAUTH client properties
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthProperties {
    private String client = "client";
    private String secret = "secret";
    private String url = "http://localhost:8082/oauth/check_token";
}
