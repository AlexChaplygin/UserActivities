package com.alex.che.useractivities.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserActivitiesDTO {
    private String userName;
    private Timestamp activityDate;
    private Long activityCount;
}
