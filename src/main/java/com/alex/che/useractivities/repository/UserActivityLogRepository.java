package com.alex.che.useractivities.repository;

import com.alex.che.useractivities.entity.UserActivitiesEntity;
import com.alex.che.useractivities.entity.UserActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserActivityLogRepository extends JpaRepository<UserActivityLogEntity, Long> {

    @Query(value = "select u.user_name as userName, date_trunc(:step, activity_date) as activityDate,\n" +
            "  sum(activity_count) as activityCount \n" +
            "from t_user_activity_log a join t_user u on a.user_id = u.user_id \n" +
            "where a.user_id in (:ids) and activity_date between :start and :end \n" +
            "group by 1,2 order by activityDate asc", nativeQuery = true)
    List<UserActivitiesEntity> findActivities(@Param("ids") List<Long> ids, @Param("step") String step, @Param("start") Date start, @Param("end") Date end);
}
