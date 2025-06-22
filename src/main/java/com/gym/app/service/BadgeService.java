package com.gym.app.service;

import com.gym.app.baseframework.exception.BaseException;
import com.gym.app.service.dto.WorkoutScheduleCompletedResponse;

public interface BadgeService {
    void evaluateAndAssignBadges(String loginId) throws BaseException;
    WorkoutScheduleCompletedResponse completeWorkout(Long workoutScheduleId) throws BaseException;
}
