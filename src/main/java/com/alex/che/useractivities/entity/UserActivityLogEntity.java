package com.alex.che.useractivities.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "T_USER_ACTIVITY_LOG")
public class UserActivityLogEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "USER_ID")
    private UserEntity user;

    @Column(name = "ACTIVITY_DATE", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp activityDate;

    @Column(name = "ACTIVITY_COUNT", nullable = false)
    private Long activityCount;
}
