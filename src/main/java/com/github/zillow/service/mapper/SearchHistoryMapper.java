package com.github.zillow.service.mapper;

import com.github.zillow.repository.entity.SearchQueryEntity;
import com.github.zillow.web.dto.SearchQueryBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

@Mapper
public interface SearchHistoryMapper {
        SearchHistoryMapper INSTANCE = Mappers.getMapper(SearchHistoryMapper.class);
        @Mapping(target="userEntity.userId", source="searchQueryBody.userId")
        @Mapping(target="query", source="searchQueryBody.query")
        SearchQueryEntity idAndSearchQueryBodyToSearchQueryEntity(Integer id, SearchQueryBody searchQueryBody);
}
