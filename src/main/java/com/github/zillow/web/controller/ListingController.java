package com.github.zillow.web.controller;

import com.github.zillow.repository.entity.ListingEntity;
import com.github.zillow.service.ListingService;
import com.github.zillow.service.SearchQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ListingController implements ApiController{
    private final ListingService listingService;
    private final  SearchQueryService searchQueryService;

    @Operation(summary = "리스팅 전체 검색하기")
    @GetMapping("/search")
    public ResponseEntity<Page<ListingEntity>> getListingData(Pageable pageable) {
        Page<ListingEntity> listings = listingService.findAllListings(pageable);
        return ResponseEntity.ok(listings);
    }

    @Operation(summary = "가격 범위로 검색하기 Price Range")
    @GetMapping("/search/price")
    public ResponseEntity<Page<ListingEntity>> getDataByPrice(@RequestParam Double minPrice, @RequestParam Double maxPrice, Pageable pageable) {
        Page<ListingEntity> listingsWithinPriceRange = listingService.findListingsWithinPriceRange(minPrice, maxPrice, pageable);
        return ResponseEntity.ok(listingsWithinPriceRange);
    }

    @Operation(summary = "zip코드로 검색하기") //이거 혹시 query로 불러오는거 있는지 찾아보기
    @GetMapping("/search/zipcode")
    public ResponseEntity<List<ListingEntity>> getDataByZipcode(@RequestParam String zipcode) {
        List<ListingEntity> listingsWithinZipcode = listingService.findListingsByZipcode(zipcode);
        return ResponseEntity.ok(listingsWithinZipcode);
    }

    @Operation(summary = "도시 이름으로 검색하기") //이거 혹시 query로 불러오는거 있는지 찾아보기
    @GetMapping("/search/city")
    public ResponseEntity<List<ListingEntity>> getDataByCity(@RequestParam String city) {
        List<ListingEntity> listingsWithinCity = listingService.findListingsByCity(city);
        return ResponseEntity.ok(listingsWithinCity);
    }

    @Operation(summary = "주 이름(State)로 검색하기") //이거 혹시 query로 불러오는거 있는지 찾아보기
    @GetMapping("/search/state")
    public ResponseEntity<List<ListingEntity>> getDataByState(@RequestParam String state) {
        List<ListingEntity> listingsWithinState = listingService.findListingsByState(state);
        return ResponseEntity.ok(listingsWithinState);
    }

    @Operation(summary = "방 개수로 검색하기")
    @GetMapping("/search/bedrooms")
    public ResponseEntity<List<ListingEntity>> getDataByBedrooms(@RequestParam Integer bedrooms) {
        List<ListingEntity> listingsFiltedByBedrooms = listingService.findListingsByBedrooms(bedrooms);
        return ResponseEntity.ok(listingsFiltedByBedrooms);
    }


    @Operation(summary = "화장실 개수로 검색하기")
    @GetMapping("/search/bathrooms")
    public ResponseEntity<List<ListingEntity>> getDataByBathrooms(@RequestParam Integer bathrooms) {
        List<ListingEntity> listingsFiltedByBathrooms = listingService.findListingsByBathrooms(bathrooms);
        return ResponseEntity.ok(listingsFiltedByBathrooms);
    }

    @Operation(summary = "집 유형(Home Type)으로 검색하기")
    @GetMapping("/search/hometype")
    public ResponseEntity<List<ListingEntity>> getDataByHomeType(@RequestParam String homeType) {
        List<ListingEntity> listingsFiltedByHomeType = listingService.findListingsByHomeType(homeType);
        return ResponseEntity.ok(listingsFiltedByHomeType);
    }

    @Operation(summary = "자주 검색하는 쿼리 저장하기(zipcode)")
    @GetMapping("/query/zipcode")
    public ResponseEntity<List<ListingEntity>> saveQueryByZipcode(@RequestParam String zipcode) {
        List<ListingEntity> listingsWithinZipcode = listingService.findListingsByZipcode(zipcode);
        String searchQuery = "zip코드로 검색: " + zipcode;
        searchQueryService.saveSearchQuery(searchQuery);
        return ResponseEntity.ok(listingsWithinZipcode);
    }

    @Operation(summary = "자주 검색하는 쿼리 저장하기(city)")
    @GetMapping("/query/city")
    public ResponseEntity<List<ListingEntity>> saveQueryByCity(@RequestParam String city) {
        List<ListingEntity> listingsWithinCity = listingService.findListingsByCity(city);
        String searchQuery = "도시로 검색: " + city;
        searchQueryService.saveSearchQuery(searchQuery);
        return ResponseEntity.ok(listingsWithinCity);
    }
}

