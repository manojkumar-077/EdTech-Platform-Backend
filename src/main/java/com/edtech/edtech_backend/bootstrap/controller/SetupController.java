package com.edtech.edtech_backend.bootstrap.controller;

import com.edtech.edtech_backend.bootstrap.dto.InitialAdminRequestDto;
import com.edtech.edtech_backend.bootstrap.service.SetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setup")
@RequiredArgsConstructor
public class SetupController {

    private final SetupService setupService;

    /**
     * One-time API to create the first SUPER_ADMIN.
     * Called via Postman only.
     */
    @PostMapping("/create-admin")
    public ResponseEntity<String> createInitialAdmin(
            @RequestBody InitialAdminRequestDto request) {

        setupService.createInitialAdmin(request);
        return ResponseEntity.ok("Super Admin created successfully");
    }
}
