package com.laalka.authservice.repositories;

import com.laalka.authservice.models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
    AuthUser findByUsername(String username);
}
