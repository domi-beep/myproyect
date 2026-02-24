package com.evertecinc.b2c.enex.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ProcessServiceApplication {

	public static void main(String[] args) {		
		SpringApplication springApplication = new SpringApplication(ProcessServiceApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

}
