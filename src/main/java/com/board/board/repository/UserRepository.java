package com.board.board.repository;

import com.board.board.dto.UserDTO;
import com.board.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);

    UserDTO findByUserIdAndPassword(String userId, String password);
}
