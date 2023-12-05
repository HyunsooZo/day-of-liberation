package com.dayofliberation.repository;

import com.dayofliberation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUUID(String uuid);
}
