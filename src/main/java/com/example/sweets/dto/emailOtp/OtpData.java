package com.example.sweets.dto.emailOtp;

import java.time.LocalDateTime;

public record OtpData(String otp, LocalDateTime expiresAt, String email) {
}
