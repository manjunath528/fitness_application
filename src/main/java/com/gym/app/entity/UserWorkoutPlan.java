package com.gym.app.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_workout_plan")
public class UserWorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_workout_plan")
    @SequenceGenerator(name = "id_sequence_workout_plan", sequenceName = "sequence_workout_plan", allocationSize = 1)
    private Long id;
    @Column(name = "login_id",nullable = false)
    private String loginId;

    @Column(name = "plan_start_date", nullable = false)
    private Timestamp planStartDate;

    @ElementCollection
    @CollectionTable(
            name = "plan_daywise_groups",
            joinColumns = @JoinColumn(name = "plan_id")
    )
    @OrderColumn(name = "day_index")
    @Column(name = "muscle_group")
    private List<String> muscleGroupSchedule;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public Timestamp getPlanStartDate() { return planStartDate; }
    public void setPlanStartDate(Timestamp planStartDate) { this.planStartDate = planStartDate; }



    public List<String> getMuscleGroupSchedule() { return muscleGroupSchedule; }
    public void setMuscleGroupSchedule(List<String> muscleGroupSchedule) { this.muscleGroupSchedule = muscleGroupSchedule; }

}