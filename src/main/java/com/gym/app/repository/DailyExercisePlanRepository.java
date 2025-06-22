package com.gym.app.repository;

import com.gym.app.entity.DailyExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyExercisePlanRepository extends JpaRepository<DailyExercisePlan, Long> {
    List<DailyExercisePlan> findByScheduleId(Long scheduleId);

}
