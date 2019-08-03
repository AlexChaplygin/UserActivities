package com.alex.che.useractivities.service;

import com.alex.che.useractivities.constants.Step;
import com.alex.che.useractivities.dto.UserActivitiesDTO;
import com.alex.che.useractivities.dto.UserActivityLogDTO;
import com.alex.che.useractivities.entity.UserActivitiesEntity;
import com.alex.che.useractivities.entity.UserActivityLogEntity;
import com.alex.che.useractivities.repository.UserActivityLogRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserActivityLogServiceImpl implements UserActivityLogService {

    private UserActivityLogRepository userActivityLogRepository;
    private ModelMapper mapper;

    @Autowired
    public UserActivityLogServiceImpl(UserActivityLogRepository userActivityLogRepository,
                                      ModelMapper mapper) {
        this.userActivityLogRepository = userActivityLogRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserActivityLogDTO> findAllUserActivityLogs() {
        List<UserActivityLogEntity> userActivityLogEntities = new ArrayList<>(userActivityLogRepository.findAll());
        return mapper.map(userActivityLogEntities, new TypeToken<List<UserActivityLogDTO>>() {
        }.getType());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserActivityLogDTO findById(Long id) {
        UserActivityLogEntity userActivityLogEntity =
                userActivityLogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        return mapper.map(userActivityLogEntity, UserActivityLogDTO.class);
    }

    @Override
    public UserActivityLogDTO saveUserActivityLog(UserActivityLogDTO userActivityLogDTO) {
        UserActivityLogEntity userActivityLogEntity =
                userActivityLogRepository.save(mapper.map(userActivityLogDTO, UserActivityLogEntity.class));
        return mapper.map(userActivityLogEntity, UserActivityLogDTO.class);
    }

    @Override
    public void deleteUserActivityLog(UserActivityLogDTO userActivityLogDTO) {
        userActivityLogRepository.delete(mapper.map(userActivityLogDTO, UserActivityLogEntity.class));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Map<Timestamp, List<UserActivitiesDTO>> getUserActivityLogProjection(List<Long> userIds, Step step, Date start, Date end) {
        List<UserActivitiesEntity> userActivitiesEntities =
                new ArrayList<>(userActivityLogRepository.findActivities(userIds, step.name(), start, end));
        List<UserActivitiesDTO> userActivitiesDTOS = mapper.map(userActivitiesEntities, new TypeToken<List<UserActivitiesDTO>>() {
        }.getType());

        Map<Timestamp, List<UserActivitiesDTO>> map = new HashMap<>();

        for (UserActivitiesDTO dto : userActivitiesDTOS) {
            if (!map.containsKey(dto.getActivityDate())) {
                List<UserActivitiesDTO> list = new ArrayList<>();
                list.add(dto);
                map.put(dto.getActivityDate(), list);
            } else {
                map.get(dto.getActivityDate()).add(dto);
            }
        }

        return map;
    }
}
