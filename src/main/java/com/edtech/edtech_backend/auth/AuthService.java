package com.edtech.edtech_backend.auth;

import com.edtech.edtech_backend.auth.dto.*;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);

    void forgotPassword(ForgotPasswordRequestDto request);

    void verifyOtp(OtpVerificationDto request);

    void resetPassword(ResetPasswordDto request);
}
