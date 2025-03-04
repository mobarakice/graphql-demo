package com.batching.app.service;

import com.batching.app.entity.ProjectEntity;
import com.batching.app.entity.UserEntity;
import com.batching.app.model.PrepopulateDto;
import com.batching.app.repository.ApiKeyRepository;
import com.batching.app.repository.ProjectRepository;
import com.batching.app.repository.UsageHistoryRepository;
import com.batching.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JsonDataLoader {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final UsageHistoryRepository usageHistoryRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        if(userRepository.count()<1) {
            jsonLoad();
        }
    }

    private void jsonLoad() {
        try {
            var objectMapper = new ObjectMapper();
            var inputStream = new ClassPathResource("db.json").getInputStream();
            var data = objectMapper.readValue(inputStream, PrepopulateDto.class);
            var userMap = userRepository.saveAll(data.users()).stream()
                    .collect(Collectors.toMap(UserEntity::getId, userEntity -> userEntity));
            var projects = data.projects()
                    .stream()
                    .peek(projectEntity -> projectEntity.setUser(userMap.get(projectEntity.getUserId())))
                    .toList();
            var projectMap = projectRepository.saveAll(projects).stream()
                    .collect(Collectors.toMap(ProjectEntity::getId, projectEntity -> projectEntity));
            var apiKeys = data.apiKeys().stream().peek(apiKeyEntity ->
                            apiKeyEntity.setProject(projectMap.get(apiKeyEntity.getProjectId())))
                    .toList();
            apiKeyRepository.saveAll(apiKeys);
            usageHistoryRepository.saveAll(data.histories());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}