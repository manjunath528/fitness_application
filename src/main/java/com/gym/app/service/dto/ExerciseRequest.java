package com.gym.app.service.dto;


import java.io.Serializable;

public class ExerciseRequest implements Serializable {
    private String name;
    private String muscleGroup;
    private String description;
    private Long workoutId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    @Override
    public String toString() {
        return "ExerciseRequest{" +
                "name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", description='" + description + '\'' +
                ", workoutId=" + workoutId +
                '}';
    }
}

