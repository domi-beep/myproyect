package com.evertecinc.b2c.enex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

    @Bean
    FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths("classpath:/templates", "classpath:/com/evertecinc/b2c/enex/email/templates");
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }
}
