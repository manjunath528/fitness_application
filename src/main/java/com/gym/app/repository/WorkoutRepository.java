package com.gym.app.repository;

import com.gym.app.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT workout FROM Workout workout WHERE workout.membershipId=:membershipId ORDER BY workout.workout ASC, workout.id ASC")
    List<Workout> workoutDetailsByMembershipId(@Param("membershipId") Long membershipId);
}
