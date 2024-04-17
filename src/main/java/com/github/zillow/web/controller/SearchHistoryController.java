//package com.github.zillow.web.controller;
//
//import com.github.zillow.repository.entity.ListingEntity;
//import com.github.zillow.service.SearchHistoryService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@Slf4j
//public class SearchHistoryController implements ApiController{
//
//    private SearchHistoryService searchHistoryService;
//
////    @Operation(summary = "자주 검색하는 리스트에 저장하기(zipcode)")
////    @GetMapping("/favorite/search/zipcode")
////    public ResponseEntity<List<ListingEntity>> getDataByZipcode(@RequestParam String zipcode) {
////        List<ListingEntity> listingsWithinZipcode = listingService.findListingsByZipcode(zipcode);
////        String searchQuery = "zip코드로 검색: " + zipcode;
////        searchHistoryService.saveSearchQuery(searchQuery);
////        return ResponseEntity.ok(listingsWithinZipcode);
////    }
//
//}
