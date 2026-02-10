package com.edtech.edtech_backend.bootstrap.service;

import com.edtech.edtech_backend.bootstrap.dto.InitialAdminRequestDto;

public interface SetupService {

    void createInitialAdmin(InitialAdminRequestDto request);
}
