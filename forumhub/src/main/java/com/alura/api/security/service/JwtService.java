package com.alura.api.security.service;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final Long timeout;

    public String encode(@NonNull Authentication auth) {
        Set<String> authorities = this.authorities(auth.getAuthorities());
        JwtClaimsSet claims = this.claims(auth, authorities);

        return jwtEncoder
            .encode(JwtEncoderParameters.from(claims))
            .getTokenValue();
    }

    public String decoder(String token) {
        return jwtDecoder
            .decode(token)
            .getSubject();
    }

    private Set<String> authorities(
        @NonNull Collection<? extends GrantedAuthority> authorities
    ) {
        return authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());
    }

    @NonNull
    private JwtClaimsSet claims(
        @NonNull Authentication auth, @NonNull Set<String> authorities
    ) {
        Instant current = Instant.now();

        return JwtClaimsSet.builder()
            .issuedAt(current)
            .expiresAt(current.plusMillis(timeout))
            .subject(auth.getName())
            .claim("authorities", authorities)
            .build();
    }
}
