package com.github.zillow.repository.listing;

import com.github.zillow.repository.entity.ListingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, Integer> {
    Page<ListingEntity> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    List<ListingEntity> findByZipcode(String zipcode);

    List<ListingEntity> findByBedrooms(Integer bedrooms);

    List<ListingEntity> findByBathrooms(Integer bathrooms);

    List<ListingEntity> findByCity(String city);

    List<ListingEntity> findByState(String state);

    List<ListingEntity> findByHomeType(String homeType);
}
