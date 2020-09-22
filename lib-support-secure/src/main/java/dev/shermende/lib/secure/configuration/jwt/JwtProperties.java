package dev.shermende.lib.secure.configuration.jwt;

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
public class JwtProperties {
    private String client = "client";
    private String secret = "secret";
}
