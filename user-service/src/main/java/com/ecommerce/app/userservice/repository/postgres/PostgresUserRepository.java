package com.ecommerce.app.userservice.repository.postgres;

import com.ecommerce.app.userservice.model.postgres.PostgresUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostgresUserRepository extends JpaRepository<PostgresUser, Long> {
    Optional<PostgresUser> findByUsername(String username);  // Corrected to use 'username'
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);  // Corrected to use 'username'
}

