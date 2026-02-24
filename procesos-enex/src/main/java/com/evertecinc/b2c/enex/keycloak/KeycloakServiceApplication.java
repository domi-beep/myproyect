package com.evertecinc.b2c.enex.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan(basePackages = { "com.evertecinc.b2c.enex" })
public class KeycloakServiceApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(KeycloakServiceApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

}
