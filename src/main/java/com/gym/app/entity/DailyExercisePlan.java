package com.gym.app.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "daily_exercise_plan")
public class DailyExercisePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_daily_exercise_plan")
    @SequenceGenerator(name = "id_sequence_daily_exercise_plan", sequenceName = "sequence_daily_exercise_plan", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="exercise_name")
    private String exerciseName;

    @Column(name="muscle_group")
    private String muscleGroup;

    @Column(name="sets")
    private Integer sets = 3;

    @Column(name="reps")
    private Integer reps = 12;

    private Boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private WorkoutSchedule schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public WorkoutSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(WorkoutSchedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DailyExercisePlan that = (DailyExercisePlan) o;
        return Objects.equals(id, that.id) && Objects.equals(exerciseName, that.exerciseName) && Objects.equals(muscleGroup, that.muscleGroup) && Objects.equals(sets, that.sets) && Objects.equals(reps, that.reps) && Objects.equals(completed, that.completed) && Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exerciseName, muscleGroup, sets, reps, completed, schedule);
    }

    @Override
    public String toString() {
        return "DailyExercisePlan{" +
                "id=" + id +
                ", exerciseName='" + exerciseName + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", completed=" + completed +
                ", schedule=" + schedule +
                '}';
    }
}

