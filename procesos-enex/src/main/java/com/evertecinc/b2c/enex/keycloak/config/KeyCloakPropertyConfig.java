package com.evertecinc.b2c.enex.keycloak.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "api.keycloak")
@Getter
@Setter
public class KeyCloakPropertyConfig {

	private String host;
	private String realm;
	private String authClientId;
	private String authClientSecret;

}
