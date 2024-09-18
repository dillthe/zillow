package com.github.zillow.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "search_query")
public class SearchQueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "query")
    private String query;

    @Column(name = "search_time")
    private ZonedDateTime searchTime;

    public ZonedDateTime getTimestamp() {
        return searchTime;
    }

    public void setTimestamp(ZonedDateTime searchTime) {
        this.searchTime = searchTime;
    }
}
