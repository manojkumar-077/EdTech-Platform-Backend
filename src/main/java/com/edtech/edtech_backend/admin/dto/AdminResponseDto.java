package com.edtech.edtech_backend.admin.dto;

import com.edtech.edtech_backend.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class AdminResponseDto {

    private Long id;
    private String email;

    public static AdminResponseDto from(User user) {
        return AdminResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
