package com.github.zillow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
@RequiredArgsConstructor
@Slf4j
@Service
public class ExternalAPIService {
    private static final String API_URL = "https://app.scrapeak.com/v1/scrapers/zillow";
    //외부 API 형식 : "https://app.scrapeak.com/v1/scrapers/zillow/property?api_key={}&zpid={ZPID}"
    private final String api_key = "90237350-6c25-4e9f-b566-40c53ce26f2d";
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


    public Page<String> getListingData(String url, Pageable pageable) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", api_key);
        queryParams.put("url", url);

        String finalUrl = buildUrl("/listing", queryParams);
        try {
            System.out.println("Final URL: " + finalUrl);
            ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class);
            String responseBody = response.getBody();

            System.out.println("API Response: " + responseBody);

             List<String> listings = new ArrayList<>();

            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), 0/*listings.size()*/);
            List<String> pageContent = listings.subList(start, end);

            return new PageImpl<>(pageContent, pageable, listings.size());

        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Failed to fetch data from external API", e);
        }
    }
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//    public String getListingDatas(String url) {
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("api_key", api_key);
//        try {
//            // URL 인코딩
////            String encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
//            queryParams.put("url", url);
//
//            String finalUrl = buildUrl("/listing", queryParams);
//            System.out.println("Final URL: " + finalUrl); // 디버깅을 위해 URL 출력
//
//            ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class);
//            return response.getBody();
//        } catch (RestClientException e) {
//            throw new RuntimeException(e);
//        }
//    }

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