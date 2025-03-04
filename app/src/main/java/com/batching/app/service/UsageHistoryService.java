package com.batching.app.service;

import com.batching.app.repository.UsageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsageHistoryService {
    private final UsageHistoryRepository repository;
}
