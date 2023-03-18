package io.piano.demo.stackexchange.middleware.util.mapper;

import io.piano.demo.stackexchange.middleware.controller.dto.out.StackExchangeSearchItemHttpDto;
import io.piano.demo.stackexchange.middleware.service.dto.out.StackExchangeSearchItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StackExchangeSearchMapper {

    StackExchangeSearchItemHttpDto toHttp(StackExchangeSearchItemDto dto);

    StackExchangeSearchItemDto toDto(io.piano.demo.stackexchange.client.http.dto.StackExchangeSearchItemHttpDto httpDto);
}
