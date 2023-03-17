package io.piano.demo.stackexchange.client.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.net.URI;
import java.time.OffsetDateTime;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class StackExchangeSearchItemHttpDto {

    @NonNull
    @JsonProperty(value = "owner")
    StackExchangeSearchItemOwnerHttpDto owner;

    @NonNull
    @JsonProperty(value = "title")
    String title;

    @JsonProperty(value = "is_answered")
    boolean answered;

    @NonNull
    @JsonProperty(value = "link")
    URI link;

    @NonNull
    @JsonProperty(value = "creation_date")
    OffsetDateTime creationDate;

}
