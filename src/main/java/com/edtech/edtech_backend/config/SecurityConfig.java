    package com.edtech.edtech_backend.config;

    import com.edtech.edtech_backend.security.JwtAuthenticationFilter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableMethodSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .authorizeHttpRequests(auth -> auth

                            // AUTH
                            .requestMatchers(
                                    "/auth/**",
                                    "/setup/**"
                            ).permitAll()

                            // SYLLABUS – ADMIN
                            .requestMatchers(HttpMethod.POST, "/syllabus/**").hasRole("SUPER_ADMIN")

                            // SYLLABUS – STUDENT & ADMIN
                            .requestMatchers(HttpMethod.GET, "/syllabus/**")
                            .hasAnyRole("STUDENT", "ADMIN", "SUPER_ADMIN")
                                    // ADMIN quiz APIs
                                    .requestMatchers("/admin/quizzes/**")
                                    .hasRole("SUPER_ADMIN")

// STUDENT quiz APIs
                                    .requestMatchers("/student/quizzes/**")
                                    .hasRole("STUDENT")

                                    // ADMIN APIs
                            .requestMatchers("/admin/**").hasRole("SUPER_ADMIN")

                            .anyRequest().authenticated()
                    )


                    .addFilterBefore(
                            jwtAuthenticationFilter,
                            UsernamePasswordAuthenticationFilter.class
                    );

            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }
    }
