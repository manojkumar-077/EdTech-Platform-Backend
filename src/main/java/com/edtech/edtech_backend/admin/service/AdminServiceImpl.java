package com.edtech.edtech_backend.admin.service;

import com.edtech.edtech_backend.admin.dto.AdminResponseDto;
import com.edtech.edtech_backend.admin.dto.CreateAdminDto;
import com.edtech.edtech_backend.common.enums.Role;
import com.edtech.edtech_backend.common.exception.BadRequestException;
import com.edtech.edtech_backend.common.exception.ResourceNotFoundException;
import com.edtech.edtech_backend.entity.User;
import com.edtech.edtech_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto createAdmin(CreateAdminDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Admin with email already exists");
        }

        User admin = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ADMIN)   // ✅ CHANGE HERE
                .isActive(true)
                .build();

        userRepository.save(admin);

        return AdminResponseDto.from(admin);
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {

        List<User> admins = userRepository.findByRoleIn(
                List.of(Role.SUPER_ADMIN, Role.ADMIN)
        );

        return admins.stream()
                .map(AdminResponseDto::from)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteAdmin(Long adminId) {

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        userRepository.delete(admin);
    }
}
