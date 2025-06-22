package com.gym.app.repository;

import com.gym.app.entity.WorkoutSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutScheduleRepository extends JpaRepository<WorkoutSchedule, Long> {
    @Query("SELECT COUNT(workoutSchedule) FROM WorkoutSchedule workoutSchedule WHERE workoutSchedule.loginId = :loginId AND workoutSchedule.completed = true")
    int countCompletedWorkoutsByLoginId(@Param("loginId") String loginId);
}
