package com.guava.deliveryms.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PD_ORDER")
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id", updatable = false, nullable = false)
    private String orderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pickup_city")
    private String pickupCity;

    @Column(name = "pickup_address")
    private String pickupAddress;

    @Column(name = "pickup_coordinates")
    private String pickupCoordinates;

    @Column(name = "pickup_contact_phone")
    private String pickupContactPhone;

    @Column(name = "delivery_city")
    private String deliveryCity;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "delivery_coordinates")
    private String deliveryCoordinates;

    @Column(name = "delivery_contact_phone")
    private String deliveryContactPhone;

    @Column(name = "status")
    private String orderStatus;

    @Column(name = "couirier_user_id")
    private String courierId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @PrePersist
    protected void onCreate() {
        modifiedDate = createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }
}
