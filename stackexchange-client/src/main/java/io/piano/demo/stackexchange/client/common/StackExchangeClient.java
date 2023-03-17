package io.piano.demo.stackexchange.client.common;

import io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchResponseHttpDto;

public interface StackExchangeClient {

    /**
     * @see <a href="https://api.stackexchange.com/docs/search">Usage of /search</a>
     */
    StackExchangeSearchResponseHttpDto search(StackExchangeSearchCriteria criteria);

}
