//package com.example.sweets.entity.user;
//
//
//import com.example.sweets.entity.base.BaseDomain;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//
//
//import java.time.LocalDateTime;
//
//import java.util.UUID;
//
//@Entity
//@Getter
//@Setter
//public class Profile  extends BaseDomain<UUID> {
//
//    @Column(nullable = true)
//    private String fullName;
//
//
//    private String email;
//
//
//    @Column(nullable = true)
//    private String phone;
//
//    private String imageUrl;
//
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//}
