package com.edtech.edtech_backend.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpVerificationDto {
    private String email;
    private String otp;
}
