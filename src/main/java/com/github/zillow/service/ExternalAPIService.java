package com.github.zillow.service;

import com.github.zillow.web.dto.ListingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Slf4j
@Service
public class ExternalAPIService {
    private static final String API_URL = "https://app.scrapeak.com/v1/scrapers/zillow";
    //외부 API 형식 : "https://app.scrapeak.com/v1/scrapers/zillow/property?api_key={}&zpid={ZPID}"
    private String api_key = "90237350-6c25-4e9f-b566-40c53ce26f2d";
     RestTemplate restTemplate = new RestTemplate();

    public String buildUrl(String path, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL)
                .path(path);

        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.toUriString();
    }


    //리스팅 안불러와짐, 왜 안돼???? 왜?????????????!
    public String getListingData(String url){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", api_key);
        queryParams.put("url", url);
        return restTemplate.getForObject(buildUrl("/listing", queryParams), String.class);
          }



    public String getDetailData(Integer zpid) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", api_key);
        queryParams.put("zpid", zpid.toString());
        return restTemplate.getForObject(buildUrl("/property", queryParams), String.class);
    }

    public String getQueryData(String query) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", api_key);
        queryParams.put("q", query);
        return restTemplate.getForObject(buildUrl("/locationSuggestions", queryParams), String.class);
    }

//    public void addToList(ListingDTO listingDTO) {
//
//    }
}




//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

//    public String buildListingPage(String path, String listingUrl, Integer pageNumber, Integer pageSize) {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL)
//                .path(path)
//                .queryParam("api_key", api_key)
//                .queryParam("url", listingUrl)
//                .queryParam("page", pageNumber)
//                .queryParam("page_size", pageSize);
//        return builder.toUriString();
//    }

//페이지 적용
//    public String getListingData(String listingUrl, Integer pageNumber, Integer pageSize) {
//        return restTemplate.getForObject(buildListingPage("/listing", listingUrl, pageNumber, pageSize), String.class);
//    }