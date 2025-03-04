package com.batching.app.repository;

import com.batching.app.entity.UsageHistoryEntity;
import com.batching.app.model.TopUsageApiProjection;
import com.batching.app.model.TopUsageKeyProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistoryEntity, BigInteger> {

    @Query(value = """
            SELECT
                u.api_key,
                u.user_id,
                COUNT(*) AS numberOfCounts
            FROM
               usage_history u
            GROUP BY
               u.api_key,
               u.user_id
            ORDER BY
               numberOfCounts DESC,
               u.api_key ASC
            """, nativeQuery = true)
    List<TopUsageKeyProjection> findTopUsageKey(Pageable pageable);

    @Query(value = """
            SELECT u.uri, COUNT(*) AS numberOfCounts
            FROM usage_history u
            GROUP BY u.uri
            ORDER BY numberOfCounts DESC, u.uri ASC
            """, nativeQuery = true)
    List<TopUsageApiProjection> findTopUsageUri();

    @Modifying
    @Transactional
    @Query("DELETE FROM UsageHistoryEntity e WHERE e.id > :value")
    void deleteAllBy(@Param("value") int value);
}
