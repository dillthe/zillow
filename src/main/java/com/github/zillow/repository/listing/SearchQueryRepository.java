package com.github.zillow.repository.listing;

import com.github.zillow.repository.entity.SearchQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchQueryRepository extends JpaRepository<SearchQueryEntity, Integer> {
}