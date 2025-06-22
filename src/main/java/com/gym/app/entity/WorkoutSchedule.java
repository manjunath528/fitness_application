package com.gym.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "workout_schedule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkoutSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_workout_schedule")
    @SequenceGenerator(name = "id_sequence_workout_schedule", sequenceName = "sequence_workout_schedule", allocationSize = 1)
    private Long id;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Column(name = "login_id",nullable = false)
    private String loginId;

    @Column(name = "day_muscle_group", nullable = false)
    private String dayMuscleGroup;

    @Column(name = "workout_date", nullable = false)
    private LocalDate workoutDate;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @ElementCollection
    @CollectionTable(
            name = "scheduled_workouts",
            joinColumns = @JoinColumn(name = "workout_schedule_id")
    )
    @Column(name = "exercise_name")
    private List<String> exercises;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDayMuscleGroup() {
        return dayMuscleGroup;
    }

    public void setDayMuscleGroup(String dayMuscleGroup) {
        this.dayMuscleGroup = dayMuscleGroup;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<String> getExercises() {
        return exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutSchedule that = (WorkoutSchedule) o;
        return completed == that.completed && Objects.equals(id, that.id) && Objects.equals(planId, that.planId) && Objects.equals(loginId, that.loginId) && Objects.equals(dayMuscleGroup, that.dayMuscleGroup) && Objects.equals(workoutDate, that.workoutDate) && Objects.equals(exercises, that.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planId, loginId, dayMuscleGroup, workoutDate, completed, exercises);
    }

    @Override
    public String toString() {
        return "WorkoutSchedule{" +
                "id=" + id +
                ", planId=" + planId +
                ", loginId='" + loginId + '\'' +
                ", dayMuscleGroup='" + dayMuscleGroup + '\'' +
                ", workoutDate=" + workoutDate +
                ", completed=" + completed +
                ", exercises=" + exercises +
                '}';
    }
}
