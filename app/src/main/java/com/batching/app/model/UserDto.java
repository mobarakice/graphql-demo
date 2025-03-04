package com.batching.app.model;

import java.math.BigInteger;

public record UserDto(
        BigInteger id,
        String name,
        String email

) {
}
