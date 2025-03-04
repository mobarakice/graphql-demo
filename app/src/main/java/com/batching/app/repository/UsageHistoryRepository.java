package com.batching.app.repository;

import com.batching.app.entity.UsageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistoryEntity, BigInteger> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UsageHistoryEntity e WHERE e.id > :value")
    void deleteAllBy(@Param("value") int value);
}
