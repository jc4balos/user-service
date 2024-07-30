package com.jc4balos.user_service.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", indexes = {

})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 256, name = "first_name")
    private String firstName;

    @Column(nullable = true, length = 256, name = "mother_surname")
    private String motherSurname;

    @Column(nullable = false, length = 256, name = "father_surname")
    private String fatherSurname;

    @Column(nullable = true, length = 256, name = "husband_surname")
    private String husbandSurname;

    @Column(nullable = false, unique = true, length = 256, name = "username")
    private String username;

    @Column(nullable = false, length = 256, name = "password")
    private String password;

    @Column(nullable = false, unique = true, length = 256, name = "email")
    private String email;

    @Column(nullable = true, length = 256, name = "address_line_1")
    private String addressLine1;

    @Column(nullable = true, length = 256, name = "address_line_2")
    private String addressLine2;

    @Column(nullable = false, name = "address_brgy_id")
    private Long addressBrgyId;

    @Column(nullable = false, name = "address_city_id")
    private Long addressCityId;

    @Column(nullable = false, name = "address_province_id")
    private Long addressProvinceId;

    @Column(nullable = false, name = "address_region_id")
    private Long addressRegionId;

    @Column(nullable = false, length = 16, name = "contact_number")
    private String contactNumber;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, name = "sex")
    private String sex;

    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, name = "is_active")
    private Boolean isActive;
}
