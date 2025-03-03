package com.batching.app.summary;

import java.util.Collections;
import java.util.List;

public record Summary(
        int totalUsers,
        int totalProjects,
        int totalKeys,
        int totalUsages,
        List<TopUsageKey> topUsageKeys,
        List<TopUsageApi> topUsageApis
) {
    public Summary(){
        this(0, 0, 0, 0, Collections.emptyList(),Collections.emptyList());
    }
}
