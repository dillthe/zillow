package com.github.zillow.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        private Integer memberId;

        @Column(name = "email")
        private String email;

        @Column(name = "name")
        private String name;

        @Column(name = "password")
        private String password;

        @Column(name = "phone_number")
        private String phoneNumber;
}
