package com.guava.authms.entity;

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
@Table(name = "PD_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "pd_user_seq")
    @SequenceGenerator(name = "pd_user_seq",
            sequenceName = "pd_user_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long userid;

    @Column(name = "user_name")
    private String username;

    @Column(name = "passcode")
    private String password;

    @Column(name = "role")
    private String role;

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
