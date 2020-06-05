package com.boa.api.repository;

import com.boa.api.domain.UserExtra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {
    
}