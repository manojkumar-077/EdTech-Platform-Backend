package com.edtech.edtech_backend.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminTestController {

    @GetMapping("/admin/test")
    public String testAdminAccess() {
        return "ADMIN ACCESS OK";
    }
}
