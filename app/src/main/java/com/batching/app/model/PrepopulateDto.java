package com.batching.app.model;

import com.batching.app.entity.ApiKeyEntity;
import com.batching.app.entity.ProjectEntity;
import com.batching.app.entity.UsageHistoryEntity;
import com.batching.app.entity.UserEntity;

import java.util.List;

public record PrepopulateDto(
        List<UserEntity> users,
        List<ProjectEntity> projects,
        List<ApiKeyEntity> apiKeys,
        List<UsageHistoryEntity> histories
) {
}