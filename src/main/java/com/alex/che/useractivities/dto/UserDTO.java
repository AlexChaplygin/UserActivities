package com.alex.che.useractivities.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserDTO {
    private Long userId;
    private String userName;
    private Set<UserActivityLogDTO> userActivityLogs;
}
