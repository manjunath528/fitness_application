package com.gym.app.repository;

import com.gym.app.entity.UserWorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkoutPlanRepository extends JpaRepository<UserWorkoutPlan, Long> {
    UserWorkoutPlan findByLoginId(String loginId);
}


