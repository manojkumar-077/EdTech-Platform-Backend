package com.edtech.edtech_backend.auth.dto;

import com.edtech.edtech_backend.common.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private String token;
    private Role role;
}
