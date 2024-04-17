package com.github.zillow.service;


import com.github.zillow.web.dto.SearchQueryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchHistoryService {
    private List<SearchQueryDTO> searchHistory = new ArrayList<>();

    public void saveSearchQuery(String query) {
        SearchQueryDTO searchQuery = new SearchQueryDTO(query);
        searchHistory.add(searchQuery);
    }

    public List<SearchQueryDTO> getSearchHistory() {
        return searchHistory;
    }

}
