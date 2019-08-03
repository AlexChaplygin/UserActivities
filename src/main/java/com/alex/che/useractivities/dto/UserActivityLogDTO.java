package com.alex.che.useractivities.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserActivityLogDTO {
    private Long id;
    private UserDTO user;
    private Timestamp activityDate;
    private Long activityCount;
}
