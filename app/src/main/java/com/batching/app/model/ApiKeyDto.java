package com.batching.app.model;

import java.math.BigInteger;
import java.util.UUID;

public record ApiKeyDto(
        BigInteger id,
        AppType appType,
        UUID apiKey,
        String packageName,
        BigInteger projectId
) {
}
