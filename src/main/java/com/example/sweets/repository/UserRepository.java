package com.example.sweets.repository;

import com.example.sweets.entity.user.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email);

  List<User> findAllByEnabledTrue();

  List<User> findAllByEnabledFalse();
}
