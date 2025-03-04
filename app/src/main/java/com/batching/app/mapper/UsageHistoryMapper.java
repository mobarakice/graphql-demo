package com.batching.app.mapper;

import com.batching.app.entity.UsageHistoryEntity;
import com.batching.app.model.UsageHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsageHistoryMapper extends BaseMapper<UsageHistoryEntity, UsageHistoryDto> {
    UsageHistoryMapper INSTANCE = Mappers.getMapper(UsageHistoryMapper.class);
}