package com.github.zillow.web.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record userDTO(Integer userId, String name, String phoneNumber) {}


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class userDTO {
//    private Integer userId;
//    private String name;
//    private String phoneNumber;
//}
