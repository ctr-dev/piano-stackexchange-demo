package io.piano.demo.stackexchange.client.http;

import io.piano.demo.stackexchange.client.BaseTest;
import io.piano.demo.stackexchange.client.common.StackExchangeSearchCriteria;
import io.piano.demo.stackexchange.client.exception.GZIPDecodeException;
import io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchResponseHttpDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Nested
class StackExchangeHttpClientMockTest extends BaseTest {

    private final StackExchangeHttpClientProperties clientProperties = new StackExchangeHttpClientProperties(
        URI.create("http://localhost")
    );

    @Mock
    private RestOperations restOperations;

    private StackExchangeHttpClient testable;

    @Override
    protected void init() {
        testable = new StackExchangeHttpClient(
            clientProperties,
            restOperations
        );
    }

    @Test
    void search_baseCase() {
        when(restOperations.exchange(any(), (Class<?>) any()))
            .then(answer -> ResponseEntity.ok(readResource("json/single-item-response.json.gz")));
        StackExchangeSearchCriteria searchCriteria = StackExchangeSearchCriteria.builder()
            .query("java")
            .build();
        StackExchangeSearchResponseHttpDto response = testable.search(searchCriteria);
        assertNotNull(response);
        assertNotNull(response.getItems());
        assertEquals(1, response.getItems().size());
    }

    @Test
    void search_notGZIPData() {
        when(restOperations.exchange(any(), (Class<?>) any()))
            .then(answer -> ResponseEntity.ok(new byte[]{0, 0}));
        StackExchangeSearchCriteria searchCriteria = StackExchangeSearchCriteria.builder()
            .query("java")
            .build();
        assertThrows(GZIPDecodeException.class, () -> testable.search(searchCriteria));
    }

}