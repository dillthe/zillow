package com.github.zillow.service;

import com.github.zillow.repository.entity.ListingEntity;
import com.github.zillow.repository.listing.ListingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    public Page<ListingEntity> findListingsWithinPriceRange(Double minPrice, Double maxPrice, Pageable pageable) {
        Page<ListingEntity> listingsFilteredByPrice = listingRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        return listingsFilteredByPrice;
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

    public List<ListingEntity> findListingsByHomeType(String homeType) {
        List<ListingEntity> listingsFilteredByHomeType = listingRepository.findByHomeType(homeType);
        return listingsFilteredByHomeType;
    }



}

