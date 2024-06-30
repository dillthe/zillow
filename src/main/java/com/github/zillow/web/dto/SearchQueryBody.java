package com.github.zillow.web.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@ToString
public class SearchQueryBody {

        private Integer userId;
        private String query;
        private ZonedDateTime searchTime;

        public SearchQueryBody(String query) {
            this.query = query;
            this.searchTime = ZonedDateTime.now();
        }
}
