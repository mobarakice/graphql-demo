package com.batching.app.model;

import java.math.BigInteger;

public interface TopUsageKeyProjection {
    String getApiKey();

    BigInteger getUserId();

    int getNumberOfCounts();
}
