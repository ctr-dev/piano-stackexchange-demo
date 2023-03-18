package io.piano.demo.stackexchange.middleware.config;

import io.piano.demo.stackexchange.client.common.StackExchangeClient;
import io.piano.demo.stackexchange.client.http.StackExchangeHttpClient;
import io.piano.demo.stackexchange.client.http.StackExchangeHttpClientProperties;
import lombok.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

import java.net.URI;

@Configuration
@ConditionalOnProperty(name = "stack-exchange.client.enabled", havingValue = "true")
@EnableConfigurationProperties(StackExchangeClientConfiguration.Properties.class)
public class StackExchangeClientConfiguration {

    @Bean
    public StackExchangeClient stackExchangeClient(
        Properties properties,
        RestOperations restOperations
    ) {
        return new StackExchangeHttpClient(
            new StackExchangeHttpClientProperties(properties.host),
            restOperations
        );
    }

    @Value
    @ConfigurationProperties(prefix = "stack-exchange.client")
    @ConstructorBinding
    static class Properties {
        URI host;
    }

}
