package com.github.zillow.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="listing_images")
public class ImageEntity {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "listing_id")
    @JsonBackReference
    private ListingEntity listingEntity;

    @Column(name = "image")
    private String image;

    @Column(name = "sequence")
    private Integer sequence;

    // Update methods for @Setter
    public void updateImage(String image) {
        this.image = image;
    }

    public void updateSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void updateListingEntity(ListingEntity listingEntity) {
        this.listingEntity = listingEntity;
    }
}