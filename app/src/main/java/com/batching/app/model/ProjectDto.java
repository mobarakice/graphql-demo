package com.batching.app.model;

import java.math.BigInteger;

public record ProjectDto(
        BigInteger id,
        String name,
        String description,
        BigInteger userId
) {
}
