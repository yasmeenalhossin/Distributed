package com.elearning.userservice.repository;

import com.elearning.userservice.model.User;
import com.elearning.userservice.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  List<User> findByRole(UserRole role);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
