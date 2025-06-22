package com.gym.app.service.dto;

public class WorkoutScheduleCompletedResponse {
    private Long workoutScheduleId;
    private String workoutScheduleStatus;

    public Long getWorkoutScheduleId() {
        return workoutScheduleId;
    }

    public void setWorkoutScheduleId(Long workoutScheduleId) {
        this.workoutScheduleId = workoutScheduleId;
    }

    public String getWorkoutScheduleStatus() {
        return workoutScheduleStatus;
    }

    public void setWorkoutScheduleStatus(String workoutScheduleStatus) {
        this.workoutScheduleStatus = workoutScheduleStatus;
    }
}
