package com.github.zillow.service;

import com.github.zillow.repository.entity.HomeType;
import com.github.zillow.repository.entity.ListingEntity;
import com.github.zillow.repository.listing.ListingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class ListingService {
    private final ListingRepository listingRepository;



    public Page<ListingEntity> findAllListings(Pageable pageable) {
        Page<ListingEntity> listings = listingRepository.findAll(pageable);
        return listings;
    }

//    public Page<ListingEntity> findListingsWithinPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
//        Page<ListingEntity> listingsFilteredByPrice = listingRepository.findByPriceBetween(minPrice, maxPrice, pageable);
//        return listingsFilteredByPrice;
//    }

    //커서 기반 페이지네이션
    public List<ListingEntity> findListingsWithinPriceRange(Double minPrice, Double maxPrice, Integer cursor, int size) {
        if (cursor == null) {
            // 첫 페이지, 커서가 없음
            return listingRepository.findTopByPriceBetweenOrderByListingIdAsc(minPrice, maxPrice, PageRequest.of(0, size));
        } else {
            // 커서를 기준으로 다음 데이터 세트를 가져옴 //커서가 데이터의 Id가 된다.
            //가격이 주어진 범위 내에 있고, ID가 주어진 값보다 큰 데이터를 ID순서로 오름차순정렬
            return listingRepository.findByPriceBetweenAndListingIdGreaterThanOrderByListingIdAsc(minPrice, maxPrice, cursor, PageRequest.of(0, size));
        }
    }

    public List<ListingEntity> findListingsByZipcode(String zipcode) {
        List<ListingEntity> listingsFilteredByZipcode = listingRepository.findByZipcode(zipcode);
        return listingsFilteredByZipcode;
    }

    public List<ListingEntity> findListingsByCity(String city) {
        List<ListingEntity> listingsilteredByCity = listingRepository.findByCity(city);
        return listingsilteredByCity;
    }

    public List<ListingEntity> findListingsByState(String state) {
        List<ListingEntity> listingsilteredByState = listingRepository.findByState(state);
        return listingsilteredByState;
    }


    public List<ListingEntity> findListingsByBedrooms(Integer bedrooms) {
        List<ListingEntity> listingsFilteredByBedrooms = listingRepository.findByBedrooms(bedrooms);
        return listingsFilteredByBedrooms;
    }

    public List<ListingEntity> findListingsByBathrooms(Integer bathrooms) {
        List<ListingEntity> listingsFilteredByBathrooms = listingRepository.findByBathrooms(bathrooms);
        return listingsFilteredByBathrooms;
    }

    public List<ListingEntity> findListingsByHomeType(HomeType homeType) {
        List<ListingEntity> listingsFilteredByHomeType = listingRepository.findByHomeType(String.valueOf(homeType));
        return listingsFilteredByHomeType;
    }



}

