package com.evertecinc.b2c.enex.process.service.impl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("CargaPrecioPizarraProcess")
@ConfigurationProperties(prefix = "process.jde.send")
@RequiredArgsConstructor
@Slf4j
public class CargaPrecioPizarraProcess implements IProcess{

	@Override
    public void run() throws ProcessException {
        log.info("Ejecutando CargaPrecioPizarraProcess");
        // ... lógica del proceso ...
    }
	
	
}
