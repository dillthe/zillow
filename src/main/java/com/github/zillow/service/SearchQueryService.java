package com.github.zillow.service;


import com.github.zillow.repository.entity.SearchQueryEntity;
import com.github.zillow.repository.listing.SearchQueryRepository;
import com.github.zillow.service.mapper.SearchHistoryMapper;
import com.github.zillow.web.dto.SearchQueryBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchQueryService {

    private final SearchQueryRepository searchQueryRepository;

    public void saveSearchQuery(String searchQuery) {
        SearchQueryEntity searchQueryEntity = new SearchQueryEntity();
//        searchQueryEntity.setUserId(searchQuery.getUserId());
        searchQueryEntity.setQuery(searchQuery);
        searchQueryEntity.setSearchTime(ZonedDateTime.now());
        searchQueryRepository.save(searchQueryEntity);
    }

//    public void saveSearchQuery(String searchQuery) {
//        SearchQueryEntity searchQueryEntity = SearchHistoryMapper.INSTANCE.idAndSearchQueryBodyToSearchQueryEntity(null,searchQuery);
//        searchQueryEntity.setQuery(searchQuery);
//        searchQueryEntity.setTimestamp(ZonedDateTime.now());
//        searchQueryRepository.save(searchQueryEntity);
//    }

    public List<SearchQueryEntity> findAllQueries() {
        List<SearchQueryEntity> queries = searchQueryRepository.findAll();
        return queries;
    }
}
