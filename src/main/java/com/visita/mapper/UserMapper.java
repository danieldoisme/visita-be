package com.visita.mapper;

import org.mapstruct.Mapper;

import com.visita.dto.request.UserCreateRequest;
import com.visita.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserEntity toUserEntity(UserCreateRequest userCreateRequest);
}
