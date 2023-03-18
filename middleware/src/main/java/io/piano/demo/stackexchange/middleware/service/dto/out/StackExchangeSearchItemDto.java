package io.piano.demo.stackexchange.middleware.service.dto.out;

import lombok.NonNull;
import lombok.Value;

import java.net.URI;
import java.time.LocalDateTime;

@Value
public class StackExchangeSearchItemDto {

    @NonNull
    String title;

    @NonNull
    String author;

    @NonNull
    LocalDateTime creationDate;

    @NonNull
    URI link;

    boolean answered;

}
