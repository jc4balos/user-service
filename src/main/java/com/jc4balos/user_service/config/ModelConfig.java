package com.jc4balos.logging_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jc4balos.logging_service.model.Logs;
import com.jc4balos.logging_service.model.ServiceComponent;

@Configuration
public class ModelConfig {
    /*
     * This class creates bean for all models.
     */

    @Bean
    public ServiceComponent serviceComponent() {
        return new ServiceComponent();
    }

    @Bean
    public Logs logs() {
        return new Logs();
    }
}
