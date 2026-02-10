package com.edtech.edtech_backend.repository;

import com.edtech.edtech_backend.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    // No extra methods needed for now
}
