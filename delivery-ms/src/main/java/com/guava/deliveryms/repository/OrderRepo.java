package com.guava.deliveryms.repository;

import com.guava.deliveryms.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, String> {

    List<OrderEntity> findAllByUserId(String userId);

    List<OrderEntity> findAllByCourierId(String courierId);

}
