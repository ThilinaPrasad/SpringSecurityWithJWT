package com.security.jwt.jwt.repositories;

import com.security.jwt.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    public User findByName(String name);
}
