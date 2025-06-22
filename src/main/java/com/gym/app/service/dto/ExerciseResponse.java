package com.gym.app.service.dto;

import java.io.Serializable;

public class ExerciseResponse implements Serializable {
    private Long id;
    private String name;
    private String muscleGroup;
    private String description;

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

    @Override
    public String toString() {
        return "ExerciseResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

