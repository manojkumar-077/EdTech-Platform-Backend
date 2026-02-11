package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.User;
import com.edtech.edtech_backend.common.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    long countByRole(Role role);
    List<User> findByRoleIn(List<Role> roles);

}
