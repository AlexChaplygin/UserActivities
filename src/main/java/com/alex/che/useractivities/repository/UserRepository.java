package com.alex.che.useractivities.repository;

import com.alex.che.useractivities.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("Select p from UserEntity p Join p.userActivityLogEntities od where od.activityDate between :start and :end")
    List<UserEntity> findByUserIdAndUserActivityLogEntitiesActivityDateBetween(@Param("start") Date start, @Param("end") Date end);
}
