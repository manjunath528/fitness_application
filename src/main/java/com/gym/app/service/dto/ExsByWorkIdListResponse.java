package com.gym.app.service.dto;

import com.gym.app.entity.Exercise;

import java.util.List;

public class ExsByWorkIdListResponse{
    private Long workoutId;
    private String workoutName;
    private List<Exercise> exercises;

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "ExsByWorkIdListResponse{" +
                "workoutId=" + workoutId +
                ", workoutName='" + workoutName + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}
