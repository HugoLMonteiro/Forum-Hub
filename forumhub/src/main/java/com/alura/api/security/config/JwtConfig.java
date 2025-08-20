package com.alura.api.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {

    @Value("${rsa.private}")
    private RSAPrivateKey privateKey;

    @Value("${rsa.public}")
    private RSAPublicKey publicKey;

    @Bean
    protected JwtEncoder jwtEncoder() {
        RSAKey key = new RSAKey
            .Builder(publicKey)
            .privateKey(privateKey)
            .build();

        JWKSet keys = new JWKSet(key);

        return new NimbusJwtEncoder(
            new ImmutableJWKSet<>(keys)
        );
    }

    @Bean
    protected JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
            .withPublicKey(publicKey)
            .build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected Long timeout(@Value("${jwt.expires}") Long timeout) {
        return timeout;
    }
}
