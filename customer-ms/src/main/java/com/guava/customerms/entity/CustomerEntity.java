package com.guava.customerms.entity;

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
@Table(name = "PD_CUSTOMER")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "pd_customer_seq")
    @SequenceGenerator(name = "pd_customer_seq",
            sequenceName = "pd_customer_seq", allocationSize = 1)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

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
