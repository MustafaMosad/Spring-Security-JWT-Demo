package com.security.jwt.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.jwt.demo.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByEmailAndEnabled(String email, boolean isEnabled);
}
