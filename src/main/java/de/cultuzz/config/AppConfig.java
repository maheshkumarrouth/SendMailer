package de.cultuzz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cultuzz.services.RefreshCallService;

@Configuration
@EnableScheduling
public class AppConfig {

	@Bean
    public RefreshCallService bean() {
        return new RefreshCallService();
    }
	
	
}
