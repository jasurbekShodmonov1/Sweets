package com.example.sweets.repository;

import com.example.sweets.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository  extends JpaRepository<Role, UUID> {

}
