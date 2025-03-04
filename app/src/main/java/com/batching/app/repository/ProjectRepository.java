package com.batching.app.repository;

import com.batching.app.entity.ProjectEntity;
import com.batching.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, BigInteger> {
}
