package jester;

import java.time.Clock;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint injectionPoint) {
        return  LoggerFactory.getLogger(Objects.requireNonNull(injectionPoint.getMethodParameter()).getContainingClass());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
