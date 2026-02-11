package com.edtech.edtech_backend.security;

import com.edtech.edtech_backend.config.JwtConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();

        // Public endpoints
        if (path.equals("/auth/login")
                || path.equals("/auth/forgot-password")
                || path.equals("/auth/verify-otp")
                || path.equals("/auth/reset-password")
                || path.startsWith("/setup")) {

            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(JwtConfig.HEADER_STRING);

        if (header != null && header.startsWith(JwtConfig.TOKEN_PREFIX)) {

            String token = header.substring(JwtConfig.TOKEN_PREFIX.length());

            if (jwtTokenProvider.validateToken(token)) {

                String email = jwtTokenProvider.getEmailFromToken(token);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    CustomUserDetails userDetails =
                            (CustomUserDetails) userDetailsService.loadUserByUsername(email);

                    if (userDetails != null) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authentication.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }

        // ALWAYS continue filter chain
        filterChain.doFilter(request, response);
    }
}