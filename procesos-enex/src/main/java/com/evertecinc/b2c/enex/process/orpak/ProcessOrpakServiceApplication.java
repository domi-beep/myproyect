package com.evertecinc.b2c.enex.process.orpak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ProcessOrpakServiceApplication {

	public static void main(String[] args) {		
		SpringApplication springApplication = new SpringApplication(ProcessOrpakServiceApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

}
