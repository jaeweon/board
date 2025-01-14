package com.board.board.service;

import com.board.board.dto.UserDTO;
import com.board.board.entity.User;
import com.board.board.exception.UserAlreadyExistsException;
import com.board.board.mapper.UserMapper;
import com.board.board.repository.UserRepository;
import com.board.board.request.UserDeleteRequest;
import com.board.board.request.UserRequest;
import com.board.board.request.UserUpdateRequest;
import com.board.board.response.UserResponse;
import com.board.board.util.BcryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse register(UserRequest userRequest) {
        // 중복된 사용자 ID 확인
        if (userRepository.existsByUserId(userRequest.getUserId())) {
            throw new UserAlreadyExistsException("User ID '" + userRequest.getUserId() + "' is already exists.");
        }

        // 비밀번호 암호화
        String encryptedPassword = BcryptUtil.encrypt(userRequest.getPassword());

        // RequestDTO -> Entity
        User user = User.builder()
                .userId(userRequest.getUserId())
                .password(encryptedPassword)
                .nickName(userRequest.getNickName())
                .isAdmin(userRequest.isAdmin())
                .isWithdraw(userRequest.isWithdraw())
                .build();

        User savedUser = userRepository.save(user);

        // 저장된 Entity -> ResponseDTO 변환
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse login(String userId, String password) {
        // 유저 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        // 비밀번호 검증
        if (!BcryptUtil.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid User Password");
        }

        // User -> UserResponse 변환
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updatePassword(UserUpdateRequest userUpdateRequest) {
        String userId = userUpdateRequest.getUserId();

        // 사용자 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // 이전 비밀번호 검증
        if (!BcryptUtil.matches(userUpdateRequest.getBeforePassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect.");
        }

        // 새 비밀번호 암호화
        String encryptedNewPassword = BcryptUtil.encrypt(userUpdateRequest.getAfterPassword());
        user.updatePassword(encryptedNewPassword);

        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    @Override
    public boolean isDuplicateId(String Id) {
        return false;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return userMapper.toDTO(user);
    }

    @Override
    public void deletedId(UserDeleteRequest userDeleteRequest) {

        // 사용자 조회
        User user = userRepository.findByUserId(userDeleteRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userDeleteRequest.getUserId()));

        // 비밀번호 검증
        if (!BcryptUtil.matches(userDeleteRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        userRepository.delete(user);
    }
}
