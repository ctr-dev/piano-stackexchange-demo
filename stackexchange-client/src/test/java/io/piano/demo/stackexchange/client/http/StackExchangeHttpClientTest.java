package io.piano.demo.stackexchange.client.http;

import io.piano.demo.stackexchange.client.common.StackExchangeSearchCriteria;
import io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchItemHttpDto;
import io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchResponseHttpDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Only for manual run")
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StackExchangeHttpClientTest {

    private StackExchangeHttpClient testable;

    @BeforeAll
    void init() {
        URI stackExchangeHost = URI.create("https://api.stackexchange.com");
        testable = new StackExchangeHttpClient(
            new StackExchangeHttpClientProperties(stackExchangeHost),
            new RestTemplate()
        );
    }

    @Test
    void search_baseCase() {
        StackExchangeSearchCriteria searchCriteria = StackExchangeSearchCriteria.builder()
            .query("java")
            .limit(5)
            .build();
        StackExchangeSearchResponseHttpDto response = testable.search(searchCriteria);
        assertNotNull(response.getItems());
        assertTrue(response.getItems().size() <= searchCriteria.getLimit());
        response.getItems().stream().map(StackExchangeSearchItemHttpDto::toString).forEach(log::info);
    }

}