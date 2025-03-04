package com.batching.app.mapper;

import com.batching.app.entity.UserEntity;
import com.batching.app.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}