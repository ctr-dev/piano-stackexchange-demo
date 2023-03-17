package io.piano.demo.stackexchange.client.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class StackExchangeSearchItemOwnerHttpDto {

    @NonNull
    @JsonProperty(value = "display_name")
    String name;

}
