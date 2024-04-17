package com.github.zillow.web.controller;

import com.github.zillow.service.ExternalAPIService;
import com.github.zillow.service.ListingService;
import com.github.zillow.service.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ExternalAPIController implements ApiController{
    private final ExternalAPIService externalAPIService;

    @GetMapping("/listing") //이건 왜 안되냐
    public String getListingData(@RequestParam String url) {
        try {
            return externalAPIService.getListingData(url);

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
        }
    }


    @GetMapping("/detail")
    public String getDetailData(@RequestParam Integer zpid) {
        try {
            return externalAPIService.getDetailData(zpid);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new InvalidValueException("JSON data를 읽을 수 없습니다.");
        }
    }

    @GetMapping("/query")
    public String getQueryData(@RequestParam("q") String query) {
        try {
            return externalAPIService.getQueryData(query);
        } catch (RuntimeException e) {
            e.printStackTrace();
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