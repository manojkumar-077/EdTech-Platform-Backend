package com.edtech.edtech_backend.auth;

import com.edtech.edtech_backend.auth.dto.*;
import com.edtech.edtech_backend.common.enums.Role;
import com.edtech.edtech_backend.common.exception.BadRequestException;
import com.edtech.edtech_backend.common.exception.ResourceNotFoundException;
import com.edtech.edtech_backend.entity.OtpToken;
import com.edtech.edtech_backend.entity.User;
import com.edtech.edtech_backend.repository.OtpTokenRepository;
import com.edtech.edtech_backend.repository.UserRepository;
import com.edtech.edtech_backend.security.JwtTokenProvider;
import com.edtech.edtech_backend.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        System.out.println("AUTH SERVICE HIT");

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        System.out.println("AUTHENTICATION SUCCESS");

        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        System.out.println("JWT TOKEN GENERATED: " + token);

        return LoginResponseDto.builder()
                .token(token)
                .role(user.getRole())
                .build();
    }

    @Override
    public void forgotPassword(ForgotPasswordRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        OtpToken otpToken = OtpToken.builder()
                .email(user.getEmail())
                .otp(otp)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .used(false)
                .build();

        otpTokenRepository.save(otpToken);

        // Email sending will be added later (MailService)
        System.out.println("OTP (for testing): " + otp);
    }

    @Override
    public void verifyOtp(OtpVerificationDto request) {

        OtpToken otpToken = otpTokenRepository
                .findByEmailAndOtpAndUsedFalse(request.getEmail(), request.getOtp())
                .orElseThrow(() -> new BadRequestException("Invalid OTP"));

        if (otpToken.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        otpToken.setUsed(true);
        otpTokenRepository.save(otpToken);
    }

    @Override
    public void resetPassword(ResetPasswordDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
