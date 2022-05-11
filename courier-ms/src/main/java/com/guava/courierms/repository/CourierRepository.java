package com.guava.courierms.repository;

import com.guava.courierms.entity.CourierEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {

    Optional<CourierEntity> getCourierEntityByCourierUserid(String userId);


}
