package com.batching.app.repository;

import com.batching.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {

    List<UserEntity> findByIdIn(List<BigInteger> ids);
}
