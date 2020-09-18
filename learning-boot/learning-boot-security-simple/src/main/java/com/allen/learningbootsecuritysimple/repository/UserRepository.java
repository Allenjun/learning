package com.allen.learningbootsecuritysimple.repository;

import com.allen.learningbootsecuritysimple.entity.DO.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/06/27 11:51:00
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByUsername(String username);
}
