package com.board.board.mapper;

import com.board.board.dto.UserDTO;
import com.board.board.entity.User;
import com.board.board.request.UserRequest;
import com.board.board.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    UserDTO toDTO(User user);

    User fromDTO(UserDTO userDTO);
}
