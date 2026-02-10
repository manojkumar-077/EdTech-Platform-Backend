package com.edtech.edtech_backend.admin.controller;

import com.edtech.edtech_backend.admin.dto.AdminResponseDto;
import com.edtech.edtech_backend.admin.dto.CreateAdminDto;
import com.edtech.edtech_backend.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // CREATE ADMIN (SUPER_ADMIN only)
    @PostMapping
    public ResponseEntity<AdminResponseDto> createAdmin(
            @RequestBody CreateAdminDto dto) {
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }

    // VIEW ALL ADMINS
    @GetMapping
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    // DELETE ADMIN
    @DeleteMapping("/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok("Admin deleted successfully");
    }
}
