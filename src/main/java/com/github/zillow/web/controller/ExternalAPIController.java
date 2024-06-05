package com.github.zillow.web.controller;

import com.github.zillow.service.ExternalAPIService;
import com.github.zillow.service.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ExternalAPIController implements ApiController{
    private final ExternalAPIService externalAPIService;


    //리스팅 데이터 출력을 못하는 문제. https://docs.scrapeak.com/zillow-scraper/endpoints/propertydetails Search Listing 참고!
    @GetMapping("/listing")
    public ResponseEntity<Page<String>> getListingData(@RequestParam String url, Pageable pageable) {
        try {
            Page<String> listings = externalAPIService.getListingData(url, pageable);
            return ResponseEntity.ok(listings);
        } catch (RuntimeException e) {
            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
        }
    }

//    @GetMapping("/listing")
//    public ResponseEntity<String> getListingData(@RequestParam String url) {
//        try {
//            String listings = externalAPIService.getListingDatas(url);
//            return ResponseEntity.ok(listings);
//
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
//        }
//    }



    @GetMapping("/detail")
    public String getDetailData(@RequestParam Integer zpid) {
        try {
            return externalAPIService.getDetailData(zpid);
        } catch (RuntimeException e) {
            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
        }
    }

    @GetMapping("/query")
    public String getQueryData(@RequestParam("q") String query) {
        try {
            return externalAPIService.getQueryData(query);
        } catch (RuntimeException e) {
            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
        }
    }

}



//    @GetMapping("/api/listing")
//    public String getListingData(@RequestParam String listingUrl, @RequestParam Integer page, @RequestParam Integer pageSize) {
//        try {
//            return listingService.getListingData(listingUrl, page, pageSize);
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
//        }
//    }