package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.OtpToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {

    Optional<OtpToken> findByEmailAndOtpAndUsedFalse(String email, String otp);

    Optional<OtpToken> findTopByEmailOrderByExpiryTimeDesc(String email);
}
