package com.guava.courierms.config;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.jwt")
@Data
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;
    private String authorizationHeader;

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(getSecretKey().getBytes());
    }
}

