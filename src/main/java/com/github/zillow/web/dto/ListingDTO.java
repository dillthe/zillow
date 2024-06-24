package com.github.zillow.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.zillow.repository.entity.HomeStatus;
import lombok.*;

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
    private double Latitude;
    private double Longitude;
    private double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer sqft;
    private HomeStatus homeStatus;
    private String homeType;
}