package com.github.zillow.service;


import com.github.zillow.repository.entity.ListingEntity;
import com.github.zillow.repository.entity.SearchQueryEntity;
import com.github.zillow.repository.listing.ListingRepository;
import com.github.zillow.repository.listing.SearchQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchQueryService {

    private final SearchQueryRepository searchQueryRepository;

    public void saveSearchQuery(String searchQuery) {
        SearchQueryEntity searchQueryEntity = new SearchQueryEntity();
        searchQueryEntity.setQuery(searchQuery);
        searchQueryEntity.setSearchTime(LocalDateTime.now());
        searchQueryRepository.save(searchQueryEntity);
    }

}
