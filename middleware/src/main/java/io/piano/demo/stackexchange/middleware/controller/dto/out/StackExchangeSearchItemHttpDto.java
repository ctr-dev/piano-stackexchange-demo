package io.piano.demo.stackexchange.middleware.controller.dto.out;

import lombok.NonNull;
import lombok.Value;

import java.net.URI;
import java.time.LocalDateTime;

@Value
public class StackExchangeSearchItemHttpDto {

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
