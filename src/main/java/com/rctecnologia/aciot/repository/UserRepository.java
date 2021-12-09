package com.rctecnologia.aciot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rctecnologia.aciot.model.User;

public interface UserRepository extends JpaRepository <User, Long> {
}
