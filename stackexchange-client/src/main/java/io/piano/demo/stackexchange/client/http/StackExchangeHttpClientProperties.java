package io.piano.demo.stackexchange.client.http;

import lombok.NonNull;
import lombok.Value;

import java.net.URI;

@Value
public class StackExchangeHttpClientProperties {

    @NonNull
    URI host;

}
