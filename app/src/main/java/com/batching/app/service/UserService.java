package com.batching.app.service;

import com.batching.app.entity.UserEntity;
import com.batching.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserEntity> findByIdIn(List<BigInteger> ids) {
        return repository.findByIdIn(ids);
    }

    public Long totalCount() {
        return repository.count();
    }
}
