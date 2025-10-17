package com.example.sweets.entity.sms;


import com.example.sweets.entity.base.BaseDomain;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_sms")
@Getter
@Setter
public class Sms extends BaseDomain<UUID> {

    private String  message;

    private String receiverEmail;

    @Enumerated(EnumType.STRING)
    private SmsStatus status;

    private LocalDateTime date;
}

