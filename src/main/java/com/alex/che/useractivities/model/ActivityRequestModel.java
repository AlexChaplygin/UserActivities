package com.alex.che.useractivities.model;

import com.alex.che.useractivities.constants.Step;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ActivityRequestModel {
    private List<Long> userIds;
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private Timestamp start;
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private Timestamp end;
    private Step step;
}
