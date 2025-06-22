package com.gym.app.repository;


import com.gym.app.entity.UserDailyDietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDailyDietPlanRepository extends JpaRepository<UserDailyDietPlan, Long> {
}
