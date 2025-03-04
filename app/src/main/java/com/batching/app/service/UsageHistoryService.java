package com.batching.app.service;

import com.batching.app.model.TopUsageApiProjection;
import com.batching.app.model.TopUsageKeyProjection;
import com.batching.app.repository.UsageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageHistoryService {
    private final UsageHistoryRepository repository;

    public List<TopUsageKeyProjection> findTopUsage(int size) {
        return repository.findTopUsageKey(PageRequest.of(0, size));
    }

    public List<TopUsageApiProjection> findTopUsageUri(){
        return repository.findTopUsageUri();
    }

    public Long totalCount() {
        return repository.count();
    }

    @Transactional
    @Modifying
    public void deleteAllBy(int value) {
        repository.deleteAllBy(value);
    }
}
