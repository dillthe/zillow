package com.github.zillow.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "interest")
public class InterestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Integer interestId;

//    @JsonBackReference //이거 넣으면 User정보가 출력이 안됨.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userEntity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", referencedColumnName = "listing_id", nullable = false)
    private ListingEntity listingEntity;


    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "price")
    private double price;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "sqft")
    private Integer sqft;

    @Column(name="home_status")
    @Enumerated(EnumType.STRING)
    private HomeStatus homeStatus;

    @Column(name="home_type")
    @Enumerated(EnumType.STRING)
    private HomeType homeType;

    public void listingEntityOf(ListingEntity listingEntity) {
        this.address = listingEntity.getAddress();
        this.city = listingEntity.getCity();
        this.state = listingEntity.getState();
        this.bedrooms = listingEntity.getBedrooms();
        this.bathrooms = listingEntity.getBathrooms();
        this.price = listingEntity.getPrice();
        this.zipcode = listingEntity.getZipcode();
        this.sqft = listingEntity.getSqft();
        this.homeStatus = HomeStatus.valueOf(listingEntity.getHomeStatus());
        this.homeType = HomeType.valueOf(listingEntity.getHomeType());
    }
}