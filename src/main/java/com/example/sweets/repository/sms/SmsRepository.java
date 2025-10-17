package com.example.sweets.repository.sms;

import com.example.sweets.entity.sms.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SmsRepository extends JpaRepository<Sms, UUID> {
}
