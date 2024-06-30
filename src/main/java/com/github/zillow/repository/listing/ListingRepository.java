package com.github.zillow.repository.listing;

import com.github.zillow.repository.entity.ListingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, Integer> {
//    Page<ListingEntity> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
    List<ListingEntity> findTopByPriceBetweenOrderByListingIdAsc(Double minPrice, Double maxPrice, Pageable pageable);
    List<ListingEntity> findByPriceBetweenAndListingIdGreaterThanOrderByListingIdAsc(Double minPrice, Double maxPrice, Integer cursor, Pageable pageable);

    List<ListingEntity> findByZipcode(String zipcode);

    List<ListingEntity> findByBedrooms(Integer bedrooms);

    List<ListingEntity> findByBathrooms(Integer bathrooms);

    List<ListingEntity> findByCity(String city);

    List<ListingEntity> findByState(String state);

    List<ListingEntity> findByHomeType(String homeType);

    @Query("SELECT l FROM ListingEntity l LEFT JOIN FETCH l.imageList WHERE l.listingId = :listingId")
    Optional<ListingEntity> findByIdWithImages(@Param("listingId") Integer listingId);

}
