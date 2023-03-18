package io.piano.demo.stackexchange.client.common;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class StackExchangeSearchCriteria {

    @NonNull String query;

    @NonNull Integer limit;

}
