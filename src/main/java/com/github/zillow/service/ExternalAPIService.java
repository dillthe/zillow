package com.github.zillow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.stream.Collectors;


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


    public Page<String> getListingData(String url, Pageable pageable) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", api_key);
        queryParams.put("url", url);

        String uri = buildUrl("/listing", queryParams);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Pageable> requestEntity = new HttpEntity<>(pageable, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            JsonNode content = root.get("content");
            String[] contentArray = objectMapper.treeToValue(content, String[].class);

//            PageImpl<String> totalElements = new PageImpl<>(Arrays.asList(contentArray), pageable, root.get("totalElements").asLong());
            List<String> contentList = Arrays.stream(contentArray)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            PageImpl<String> totalElements = new PageImpl<>(contentList, pageable, root.get("totalElements").asLong());


            return totalElements;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public Page<String> getListingData(String url, Pageable pageable){
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("api_key", api_key);
//        queryParams.put("url", url);
//        return restTemplate.getForObject(buildUrl("/listing", queryParams), String.class, pageable);
//          }



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