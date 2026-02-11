package com.edtech.edtech_backend.bootstrap.service;

import com.edtech.edtech_backend.bootstrap.dto.InitialAdminRequestDto;
import com.edtech.edtech_backend.common.enums.Role;
import com.edtech.edtech_backend.entity.User;
import com.edtech.edtech_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetupServiceImpl implements SetupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createInitialAdmin(InitialAdminRequestDto request) {

        // Allow only one SUPER_ADMIN
//        if (userRepository.countByRole(Role.SUPER_ADMIN) > 0) {
//            throw new RuntimeException("Super Admin already exists");
//        }

        User admin = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.SUPER_ADMIN)
                .isActive(true)
                .build();

        userRepository.save(admin);
    }
}
