package com.gym.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "exercise")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_exercise")
    @SequenceGenerator(name = "id_sequence_exercise", sequenceName = "sequence_exercise", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Workout workout;

    @Column(name="workout_id",insertable = false, updatable = false)
    private Long workoutId;

    @Column(name = "exercise_name")
    private String exerciseName;

    @Column(name="muscle_group")
    private String muscleGroup;

    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long exerciseId) {
        this.id = exerciseId;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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
        return "Exercise{" +
                "exerciseId=" + id +
                ", workout=" + workout +
                ", workoutId=" + workoutId +
                ", exerciseName='" + exerciseName + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

