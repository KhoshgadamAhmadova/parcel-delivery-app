package com.guava.courierms.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PD_COURIER")
public class CourierEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "pd_courier_seq")
    @SequenceGenerator(name = "pd_courier_seq",
            sequenceName = "pd_courier_seq", allocationSize = 1)
    @Column(name = "courier_id", nullable = false, unique = true)
    private Long courierId;

    @Column(name = "user_id")
    private String courierUserid;

    @Column(name = "courier_type")
    private String courierType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @PrePersist
    protected void onCreate() {
        modifiedDate = createdDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDate.now();
    }
}
