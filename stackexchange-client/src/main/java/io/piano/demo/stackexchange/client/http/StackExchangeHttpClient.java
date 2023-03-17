package io.piano.demo.stackexchange.client.http;

import io.piano.demo.stackexchange.client.common.StackExchangeClient;
import io.piano.demo.stackexchange.client.common.StackExchangeSearchCriteria;
import io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchResponseHttpDto;
import io.piano.demo.stackexchange.client.util.GZIPUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class StackExchangeHttpClient implements StackExchangeClient {

    private final StackExchangeHttpClientProperties properties;
    private final RestOperations restOperations;

    @SneakyThrows
    @Override
    public StackExchangeSearchResponseHttpDto search(StackExchangeSearchCriteria criteria) {
        URI url = UriComponentsBuilder.fromUri(properties.getHost())
            .path("/2.3/search")
            .queryParam("intitle", criteria.getQuery())
            .queryParam("site", "stackoverflow")
            .queryParam("order", "desc")
            .queryParam("sort", "activity")
            .queryParam("pagesize", 5)
            .build()
            .toUri();
        RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.GET, url);
        // Return GZIP data
        ResponseEntity<byte[]> responseEntity = restOperations.exchange(
            requestEntity,
            byte[].class
        );
        return GZIPUtil.decode(
            Objects.requireNonNull(responseEntity.getBody()),
            StackExchangeSearchResponseHttpDto.class
        );
    }

}
