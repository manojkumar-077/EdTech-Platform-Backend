package com.edtech.edtech_backend.admin.service;

import com.edtech.edtech_backend.admin.dto.AdminResponseDto;
import com.edtech.edtech_backend.admin.dto.CreateAdminDto;

import java.util.List;

public interface AdminService {

    AdminResponseDto createAdmin(CreateAdminDto dto);

    List<AdminResponseDto> getAllAdmins();

    void deleteAdmin(Long adminId);
}
