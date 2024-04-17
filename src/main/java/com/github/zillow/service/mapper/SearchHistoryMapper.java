package com.github.zillow.service.mapper;

import com.github.zillow.repository.entity.InterestEntity;
import com.github.zillow.web.dto.InterestBody;
import com.github.zillow.web.dto.SearchQueryBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.core.annotation.MergedAnnotations;

@Mapper
public interface SearchHistoryMapper {
        com.github.zillow.service.mapper.SearchHistoryMapper INSTANCE = Mappers.getMapper(com.github.zillow.service.mapper.SearchHistoryMapper.class);

        InterestEntity idAndSearcyQueryBodyToSearchQueryEntity(Integer id, SearchQueryBody searchQueryBody);

}
