package com.batching.app.summary;

import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @QueryMapping
    public Mono<Summary> summary(@Argument Integer topCount, DataFetchingEnvironment env) {
        env.getGraphQlContext().put("topCount", topCount);
        return Mono.just(new Summary());
    }

    @SchemaMapping(typeName = "Summary", field = "totalUsers")
    public Mono<Integer> totalUsers() {
        return Mono.just(10);
    }

    @SchemaMapping(typeName = "Summary", field = "totalProjects")
    public Mono<Integer> totalProjects() {
        return Mono.just(30);
    }

    @SchemaMapping(typeName = "Summary", field = "totalKeys")
    public Mono<Integer> totalKeys() {
        return Mono.just(50);
    }

    @SchemaMapping(typeName = "Summary", field = "totalUsages")
    public Mono<Integer> totalUsages() {
        return Mono.just(100000);
    }

    @SchemaMapping(typeName = "Summary", field = "topUsagesKeys")
    public Mono<List<TopUsageKey>> topUsagesKeys(Summary summary, DataFetchingEnvironment env) {
        Integer topCount = env.getGraphQlContext().getOrDefault("topCount", 3);
        List<TopUsageKey> list = new ArrayList<>();
        for (int i = 1; i <= topCount; i++) {
            list.add(new TopUsageKey("com.example." + i, "username" + i, 100 * i));
        }
        return Mono.just(list);
    }

    @BatchMapping(typeName = "Summary", field = "topUsageApis")
    public Mono<Map<Summary, List<TopUsageApi>>> topUsageApis(List<Summary> summary, GraphQLContext env) {
        Integer topCount = env.getOrDefault("topCount", 3);
        List<TopUsageApi> list = new ArrayList<>();
        for (int i = 1; i <= topCount; i++) {
            list.add(new TopUsageApi("api" + i, 100 * i));
        }
        return Mono.just(Map.of(summary.getFirst(), list));
    }
}
