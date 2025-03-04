package com.batching.app.model;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

public record UsageHistoryDto(
        BigInteger id,
        String uri,
        int code,
        RequestType method,
        AppType appType,
        UUID apiKey,
        String packageName,
        BigInteger projectId,
        BigInteger userId,
        Instant createdAt
) {
}
