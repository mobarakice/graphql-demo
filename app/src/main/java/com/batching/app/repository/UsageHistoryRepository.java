package com.batching.app.repository;

import com.batching.app.entity.UsageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistoryEntity, BigInteger> {
}
