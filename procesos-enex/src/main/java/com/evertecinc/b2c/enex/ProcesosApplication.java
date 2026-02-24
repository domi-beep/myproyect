package com.evertecinc.b2c.enex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication(scanBasePackages = "com.evertecinc.b2c.enex")
@EnableEncryptableProperties
@ComponentScan(basePackages = {"com.evertecinc.b2c.enex"})
public class ProcesosApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ProcesosApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

}

