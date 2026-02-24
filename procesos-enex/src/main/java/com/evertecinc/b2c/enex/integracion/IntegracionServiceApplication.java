package com.evertecinc.b2c.enex.integracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication(scanBasePackages = {
	    "com.evertecinc.b2c.enex.integracion",
	    "com.evertecinc.b2c.enex.saf",
	    "com.evertecinc.b2c.enex.client",
	    "com.evertecinc.b2c.enex.audits"
	})
@EnableEncryptableProperties
public class IntegracionServiceApplication {

	public static void main(String[] args) {		
		SpringApplication springApplication = new SpringApplication(IntegracionServiceApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

}
