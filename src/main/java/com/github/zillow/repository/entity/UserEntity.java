package com.github.zillow.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Integer userId;

        @Column(name = "email")
        private String email;

        @Column(name = "name")
        private String name;

        @Column(name = "password")
        private String password;

        @Column(name = "phone_number")
        private String phoneNumber;
}
