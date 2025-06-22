package com.gym.app.service.dto;


import java.io.Serializable;
import java.util.List;

public class WorkoutResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<ExerciseResponse> exercises;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExerciseResponse> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseResponse> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "WorkoutResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}

