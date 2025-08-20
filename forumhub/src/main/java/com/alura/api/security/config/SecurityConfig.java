package com.alura.api.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain chains(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.oauth2ResourceServer(config ->
            config.jwt(jwt -> jwt.jwtAuthenticationConverter(this.buildAuthConverter()))
        );
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.POST, "/api/v1/users/auth/login").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/v1/users/auth/register").permitAll();
            authorize.requestMatchers("/api/v1/swagger-ui/**").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasAuthority("BASE_USER");
            authorize.anyRequest().authenticated();
        });

        return http.build();
    }

    @NonNull
    private JwtAuthenticationConverter buildAuthConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("authorities");
        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return authenticationConverter;
    }

}
