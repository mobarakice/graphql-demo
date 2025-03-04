package com.batching.app.service;

import com.batching.app.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiKeyService {
    private final ApiKeyRepository repository;
}
