package com.alex.che.useractivities.service;

import com.alex.che.useractivities.constants.Step;
import com.alex.che.useractivities.dto.UserActivitiesDTO;
import com.alex.che.useractivities.dto.UserActivityLogDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserActivityLogService {
    List<UserActivityLogDTO> findAllUserActivityLogs();

    UserActivityLogDTO findById(Long id);

    UserActivityLogDTO saveUserActivityLog(UserActivityLogDTO userActivityLogDTO);

    void deleteUserActivityLog(UserActivityLogDTO userActivityLogEntity);

    Map<Timestamp, List<UserActivitiesDTO>> getUserActivityLogProjection(List<Long> userIds, Step step, Date start, Date end);
}
