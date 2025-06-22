package com.gym.app.service.dto;

import java.util.List;

public class PlanSelectionRequest {
    private String loginId;
    private List<List<String>> muscleGroupSchedule; // e.g., [["Chest", "Triceps"], ["Back", "Biceps"]]

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public List<List<String>> getMuscleGroupSchedule() { return muscleGroupSchedule; }
    public void setMuscleGroupSchedule(List<List<String>> muscleGroupSchedule) { this.muscleGroupSchedule = muscleGroupSchedule; }
}
