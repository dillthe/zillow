package com.github.zillow.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ListingDTO {
    private Integer listingId;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private BigDecimal Latitude;
    private BigDecimal Longitude;
    private Double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer sqft;
    private String homeStatus;
    private String homeType;
}