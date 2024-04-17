package com.github.zillow.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "listing")
public class ListingEntity {

    @Id
    @Column(name= "listing_id")
    private Integer listingId;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "price")
    private double price;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "sqft")
    private Integer sqft;

    @Column(name="home_status")
    private String homeStatus;

    @Column(name="home_type")
    private String homeType;
}
//    @JsonProperty("sqft")
//@JsonIgnoreProperties(ignoreUnknown = true)

//    @JsonProperty("homeStatus")
//    @JsonProperty("bathrooms")
//    @JsonProperty("bedrooms")
//    @JsonProperty("price")
//    @JsonProperty("longitude")
//    @JsonProperty("latitude")
//    @JsonProperty("zipcode")
//    @JsonProperty("streetAddress")
//    @JsonProperty("zpid")
