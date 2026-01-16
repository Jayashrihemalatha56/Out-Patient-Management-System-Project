package com.outpatientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
