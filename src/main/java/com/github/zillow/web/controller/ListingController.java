package com.github.zillow.web.controller;

import com.github.zillow.repository.entity.HomeType;
import com.github.zillow.repository.entity.ListingEntity;
import com.github.zillow.repository.entity.SearchQueryEntity;
import com.github.zillow.service.ListingService;
import com.github.zillow.service.SearchQueryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @Operation(summary = "가격 범위로 검색하기 Price Range")
//    @GetMapping("/search/price")
//    public ResponseEntity<Page<ListingEntity>> getDataByPrice(@RequestParam @Positive Double minPrice, @RequestParam @Positive Double maxPrice, Pageable pageable) {
//        Page<ListingEntity> listingsWithinPriceRange = listingService.findListingsWithinPriceRange(minPrice, maxPrice, pageable);
//        return ResponseEntity.ok(listingsWithinPriceRange);
//    }


    //커서 기반 페이징으로 바꾸기. Cursor-based Paging(대규모 데이터를 다룰 때 유용)
    @Operation(summary = "가격 범위로 검색하기 Price Range")
    @GetMapping("/search/price")
    public List<ListingEntity> getDataByPrice(
        @RequestParam @Positive Double minPrice,
        @RequestParam @Positive Double maxPrice,
        @RequestParam(required = false) Integer cursor,
        @RequestParam @Positive int size){
            List<ListingEntity> listingsWithinPriceRange
                    = listingService.findListingsWithinPriceRange(minPrice, maxPrice, cursor, size);
          return listingsWithinPriceRange;
    }


    @Operation(summary = "zip코드로 검색하기") //이거 혹시 query로 불러오는거 있는지 찾아보기
    @GetMapping("/search/zipcode")
    public List<ListingEntity> getDataByZipcode(@RequestParam @Positive String zipcode, HttpServletResponse response) {
        List<ListingEntity> listingsWithinZipcode = listingService.findListingsByZipcode(zipcode);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return listingsWithinZipcode;
    }

    @Operation(summary = "도시 이름으로 검색하기") //이거 혹시 query로 불러오는거 있는지 찾아보기
    @GetMapping("/search/city")
    public List<ListingEntity> getDataByCity(@RequestParam String city, HttpServletResponse response) {
        List<ListingEntity> listingsWithinCity = listingService.findListingsByCity(city);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return listingsWithinCity;
    }

    @Operation(summary = "주 이름(State)로 검색하기") //이거 혹시 query로 불러오는거 있는지 찾아보기
    @GetMapping("/search/state")
    public List<ListingEntity> getDataByState(@RequestParam String state, HttpServletResponse response) {
        List<ListingEntity> listingsWithinState = listingService.findListingsByState(state);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return listingsWithinState;
    }

    @Operation(summary = "방 개수로 검색하기")
    @GetMapping("/search/bedrooms")
    public List<ListingEntity> getDataByBedrooms(@RequestParam @Positive Integer bedrooms, HttpServletResponse response) {
        List<ListingEntity> listingsFiltedByBedrooms = listingService.findListingsByBedrooms(bedrooms);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return listingsFiltedByBedrooms;
    }


    @Operation(summary = "화장실 개수로 검색하기")
    @GetMapping("/search/bathrooms")
    public List<ListingEntity> getDataByBathrooms(@RequestParam @Positive Integer bathrooms, HttpServletResponse response) {
        List<ListingEntity> listingsFiltedByBathrooms = listingService.findListingsByBathrooms(bathrooms);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return listingsFiltedByBathrooms;
    }

    @Operation(summary = "집 유형(Home Type)으로 검색하기")
    @GetMapping("/search/hometype")
    public List<ListingEntity> getDataByHomeType(@RequestParam HomeType homeType, HttpServletResponse response) {
        List<ListingEntity> listingsFiltedByHomeType = listingService.findListingsByHomeType(homeType);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return listingsFiltedByHomeType;
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

    @Operation(summary = "저장한 쿼리 전체보기")
    @GetMapping("/query/saved")
    public ResponseEntity<List<SearchQueryEntity>> getQueryData() {
        List<SearchQueryEntity> savedQuery = searchQueryService.findAllQueries();
        return ResponseEntity.ok(savedQuery);
    }
}

