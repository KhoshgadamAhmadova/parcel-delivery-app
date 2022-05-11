package com.guava.authms.repository;


import com.guava.authms.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryUserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String userName);

}
