package com.batching.app.summary;

import com.batching.app.entity.UserEntity;
import com.batching.app.model.TopUsageKeyProjection;
import com.batching.app.service.ApiKeyService;
import com.batching.app.service.ProjectService;
import com.batching.app.service.UsageHistoryService;
import com.batching.app.service.UserService;
import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;
    private final UserService userService;
    private final ProjectService projectService;
    private final ApiKeyService apiKeyService;
    private final UsageHistoryService usageHistoryService;

    @QueryMapping
    public Mono<Summary> summary(@Argument Integer topCount, DataFetchingEnvironment env) {
        env.getGraphQlContext().put("topCount", topCount);
        return Mono.just(new Summary());
    }

    @SchemaMapping(typeName = "Summary", field = "totalUsers")
    public Mono<Integer> totalUsers() {
        return Mono.just(userService.totalCount().intValue());
    }

    @SchemaMapping(typeName = "Summary", field = "totalProjects")
    public Mono<Integer> totalProjects() {
        return Mono.just(projectService.totalCount().intValue());
    }

    @SchemaMapping(typeName = "Summary", field = "totalKeys")
    public Mono<Integer> totalKeys() {
        return Mono.just(apiKeyService.totalCount().intValue());
    }

    @SchemaMapping(typeName = "Summary", field = "totalUsages")
    public Mono<Integer> totalUsages() {
        return Mono.just(usageHistoryService.totalCount().intValue());
    }

    @SchemaMapping(typeName = "Summary", field = "topUsagesKeys")
    public Mono<List<TopUsageKey>> topUsagesKeys(Summary summary, DataFetchingEnvironment env) {
        int topCount = env.getGraphQlContext().getOrDefault("topCount", 3);
        var topUsage = usageHistoryService.findTopUsage(topCount);
        var userIds = topUsage.stream().map(TopUsageKeyProjection::getUserId).toList();
        var userMap = userService.findByIdIn(userIds).stream()
                .collect(Collectors.toMap(UserEntity::getId, UserEntity::getEmail));
        var usages = topUsage.stream().map(projection ->
                new TopUsageKey(projection.getApiKey(),
                        userMap.get(projection.getUserId()),
                        projection.getNumberOfCounts())).toList();

        return Mono.just(usages);
    }

    @BatchMapping(typeName = "Summary", field = "topUsageApis")
    public Mono<Map<Summary, List<TopUsageApi>>> topUsageApis(List<Summary> summary, GraphQLContext env) {
        int topCount = env.getOrDefault("topCount", 3);
        var topApiUsages = usageHistoryService.findTopUsageUri().stream().map(
                item-> new TopUsageApi(item.getUri(),
                item.getNumberOfCounts()))
                .toList();
        return Mono.just(Map.of(summary.getFirst(), topApiUsages));
    }
}
