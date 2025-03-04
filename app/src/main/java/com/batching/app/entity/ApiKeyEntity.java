package com.batching.app.entity;

import com.batching.app.model.AppType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "api_keys")
public class ApiKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Enumerated(value = EnumType.STRING)
    private AppType  appType;
    private UUID apiKey;
    private String packageName;
    @Column(updatable = false, insertable = false)
    private BigInteger projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private ProjectEntity project;
}
