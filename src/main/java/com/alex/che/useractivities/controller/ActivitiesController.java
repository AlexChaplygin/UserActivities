package com.alex.che.useractivities.controller;

import com.alex.che.useractivities.dto.UserActivitiesDTO;
import com.alex.che.useractivities.dto.UserDTO;
import com.alex.che.useractivities.model.ActivityRequestModel;
import com.alex.che.useractivities.service.UserActivityLogService;
import com.alex.che.useractivities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {

    private UserService userService;
    private UserActivityLogService userActivityLogService;

    @Autowired
    public ActivitiesController(UserService userService,
                                UserActivityLogService userActivityLogService) {
        this.userService = userService;
        this.userActivityLogService = userActivityLogService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTOS);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<Timestamp, List<UserActivitiesDTO>>> getUserActivities(@RequestBody ActivityRequestModel activityRequestModel) {
        Map<Timestamp, List<UserActivitiesDTO>> userActivityLogDTOS =
                userActivityLogService.getUserActivityLogProjection(
                        activityRequestModel.getUserIds(),
                        activityRequestModel.getStep(),
                        activityRequestModel.getStart(),
                        activityRequestModel.getEnd());
        return ResponseEntity.status(HttpStatus.CREATED).body(userActivityLogDTOS);
    }

}
