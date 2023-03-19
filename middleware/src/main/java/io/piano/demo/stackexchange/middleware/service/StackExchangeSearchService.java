package io.piano.demo.stackexchange.middleware.service;

import io.piano.demo.stackexchange.client.common.StackExchangeClient;
import io.piano.demo.stackexchange.client.common.StackExchangeSearchCriteria;
import io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchResponseHttpDto;
import io.piano.demo.stackexchange.middleware.service.dto.out.StackExchangeSearchItemDto;
import io.piano.demo.stackexchange.middleware.util.mapper.StackExchangeSearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StackExchangeSearchService {

    private final StackExchangeSearchMapper stackExchangeSearchMapper;
    private final StackExchangeClient client;

    public Collection<StackExchangeSearchItemDto> execute(String query, int limit) {
        log.info("Start search for query {} with limit {}", query, limit);
        StackExchangeSearchCriteria criteria = StackExchangeSearchCriteria.builder()
            .query(query)
            .limit(limit)
            .build();
        StackExchangeSearchResponseHttpDto response = client.search(criteria);
        return response.getItems().stream()
            .map(stackExchangeSearchMapper::toDto)
            .collect(Collectors.toUnmodifiableList());
    }

}
