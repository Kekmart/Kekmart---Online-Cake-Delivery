package com.kekmart.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kekmart.application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
