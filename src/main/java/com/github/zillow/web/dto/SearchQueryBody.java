package com.github.zillow.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class SearchQueryBody {

    private Integer memberId;
        private String query;
        private LocalDateTime searchTime;

        public SearchQueryBody(String query) {
            this.query = query;
            this.searchTime = LocalDateTime.now();
        }
}
