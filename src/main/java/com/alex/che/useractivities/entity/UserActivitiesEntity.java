package com.alex.che.useractivities.entity;

import java.sql.Timestamp;

public interface UserActivitiesEntity {
    String getUserName();
    Timestamp getActivityDate();
    Long getActivityCount();
}
