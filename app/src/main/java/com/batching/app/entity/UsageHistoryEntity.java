package com.batching.app.entity;

import com.batching.app.model.AppType;
import com.batching.app.model.RequestType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "usage_history")
public class UsageHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String uri;
    private int code;
    @Enumerated(value = EnumType.STRING)
    private RequestType method;
    @Enumerated(value = EnumType.STRING)
    private AppType  appType;
    private UUID apiKey;
    private String packageName;
    private BigInteger projectId;
    private BigInteger userId;
    @CreatedDate
    private Instant createdAt;
}
