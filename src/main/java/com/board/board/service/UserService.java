package com.board.board.service;

import com.board.board.dto.UserDTO;
import com.board.board.request.UserDeleteRequest;
import com.board.board.request.UserRequest;
import com.board.board.request.UserUpdateRequest;
import com.board.board.response.UserResponse;

public interface UserService {

    UserResponse register(UserRequest userRequest);

    UserResponse login(String Id, String password);

    boolean isDuplicateId(String Id);

    UserDTO getUserInfo(String userId);

    UserResponse updatePassword(UserUpdateRequest userUpdateRequest);

    void deletedId(UserDeleteRequest userDeleteRequest);

}
