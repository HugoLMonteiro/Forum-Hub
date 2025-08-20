package com.alura.api.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private final SecurityRequirement securityRequirement = new SecurityRequirement();
    private final Components components = new Components();
    public static final String BEARER_NAME = "bearerAuth";
    private static final String BEARER_SCHEME = "bearer";
    public static final String BASIC_NAME = "basicAuth";
    private static final String BASIC_SCHEME = "basic";
    private static final String BEARER_FORMAT = "JWT";

    @Bean
    public OpenAPI customOpenAPI() {
        this.buildBearerScheme();
        this.buildBasicScheme();

        return new OpenAPI()
            .info(this.getInfo())
            .addSecurityItem(securityRequirement)
            .components(components);
    }

    private void buildBearerScheme() {
        this.addSecurityRequirement(BEARER_NAME);
        this.addComponents(
            BEARER_NAME, getSchemeWithBearerFormat()
        );
    }

    private void buildBasicScheme() {
        this.addSecurityRequirement(BASIC_NAME);
        this.addComponents(
            BASIC_NAME, getBaseScheme(BASIC_NAME, BASIC_SCHEME)
        );
    }

    private Info getInfo() {
        return new Info()
            .title("ForumHub API")
            .version("0.1")
            .description("Challenge Alura - ForumHub API");
    }

    private SecurityScheme getBaseScheme(String name, String scheme) {
        return new SecurityScheme()
            .name(name)
            .type(SecurityScheme.Type.HTTP)
            .scheme(scheme);
    }

    private SecurityScheme getSchemeWithBearerFormat() {
        return
            getBaseScheme(BEARER_NAME, BEARER_SCHEME)
            .bearerFormat(BEARER_FORMAT);
    }

    private void addSecurityRequirement(String method) {
        securityRequirement.addList(method);
    }

    private void addComponents(String method, SecurityScheme scheme) {
        components.addSecuritySchemes(method, scheme);
    }
}
