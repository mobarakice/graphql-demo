package com.batching.app.repository;

import com.batching.app.entity.ApiKeyEntity;
import com.batching.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, BigInteger> {
}
