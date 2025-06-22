package com.gym.app.repository;

import com.gym.app.entity.Token;
import com.gym.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByUser(User user);
    Optional<Token> findByTokenAndExpiredFalse(String token);

}
