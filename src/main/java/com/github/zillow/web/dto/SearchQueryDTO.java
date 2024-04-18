package com.github.zillow.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class SearchQueryDTO {
        private Integer id;
//        private Integer memberId;
        private String query;
        private LocalDateTime searchTime;

     //   public SearchQueryDTO(){}
    //NoArgsConstructor를 만들지 않은 이유는 해당 클래스의 필드들이 모두 초기화되어야 하는데, query 필드는 생성자를 통해 초기화되어야 하기 때문입니다. 만약 NoArgsConstructor를 추가한다면, query 필드가 초기화되지 않은 상태로 객체가 생성될 수 있으며, 이는 의도하지 않은 동작을 초래할 수 있습니다. 때문에, query 필드를 초기화하는 생성자가 있기 때문에 별도로 NoArgsConstructor를 만들지 않는 것이 바람직합니다.

        public SearchQueryDTO(String query) {
            this.query = query;
            this.searchTime = LocalDateTime.now();
        }

}
