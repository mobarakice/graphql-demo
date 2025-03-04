package com.batching.app.service;

import com.batching.app.repository.UsageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsageHistoryService {
    private final UsageHistoryRepository repository;

    @Transactional
    @Modifying
    public void deleteAllBy(int value){
        repository.deleteAllBy(value);
    }
}
