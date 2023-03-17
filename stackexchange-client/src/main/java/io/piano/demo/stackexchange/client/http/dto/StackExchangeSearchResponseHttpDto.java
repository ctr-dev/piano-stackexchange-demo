package io.piano.demo.stackexchange.client.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class StackExchangeSearchResponseHttpDto {

    @NonNull
    Collection<StackExchangeSearchItemHttpDto> items;

}
