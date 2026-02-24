package com.evertecinc.b2c.enex.keycloak.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KeyCloakConfig {

    private final KeyCloakPropertyConfig keyCloakPropertyConfig;

    @Bean
    public Keycloak keycloak() {

    	return KeycloakBuilder.builder()
    			.serverUrl(keyCloakPropertyConfig.getHost())
    			.realm(keyCloakPropertyConfig.getRealm())
    			.clientId(keyCloakPropertyConfig.getAuthClientId())
    			.clientSecret(keyCloakPropertyConfig.getAuthClientSecret())
    			.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
    			.build();
    }
}
