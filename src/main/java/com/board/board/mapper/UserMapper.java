package com.board.board.mapper;

import com.board.board.dto.UserDTO;
import com.board.board.entity.User;
import com.board.board.request.UserRequest;
import com.board.board.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toResponse(User user);

    UserDTO toDTO(User user);

    User fromDTO(UserDTO userDTO);
}
