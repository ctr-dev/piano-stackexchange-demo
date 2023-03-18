package io.piano.demo.stackexchange.middleware.controller;

import io.piano.demo.stackexchange.middleware.controller.dto.out.StackExchangeSearchItemHttpDto;
import io.piano.demo.stackexchange.middleware.service.StackExchangeSearchService;
import io.piano.demo.stackexchange.middleware.util.mapper.StackExchangeSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stack-exchange")
@RequiredArgsConstructor
public class StackExchangeHttpController {

    private final StackExchangeSearchMapper stackExchangeSearchMapper;
    private final StackExchangeSearchService stackExchangeSearchService;

    @GetMapping("/search")
    public Collection<StackExchangeSearchItemHttpDto> search(
        @RequestParam(name = "query") String query,
        @RequestParam(name = "limit", required = false, defaultValue = "5") int limit
    ) {
        return stackExchangeSearchService.execute(query, limit).stream()
            .map(stackExchangeSearchMapper::toHttp)
            .collect(Collectors.toUnmodifiableList());
    }

}
