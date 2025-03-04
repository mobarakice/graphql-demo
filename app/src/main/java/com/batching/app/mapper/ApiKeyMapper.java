package com.batching.app.mapper;

import com.batching.app.entity.ApiKeyEntity;
import com.batching.app.model.ApiKeyDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiKeyMapper extends BaseMapper<ApiKeyEntity, ApiKeyDto> {
    ApiKeyMapper INSTANCE = Mappers.getMapper(ApiKeyMapper.class);
}