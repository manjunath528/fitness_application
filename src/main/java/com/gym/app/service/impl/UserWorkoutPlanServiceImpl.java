package com.gym.app.service.impl;

import com.gym.app.baseframework.exception.BaseException;
import com.gym.app.common.DateTimeFormatterUtil;
import com.gym.app.entity.DailyExercisePlan;
import com.gym.app.entity.Exercise;
import com.gym.app.entity.UserWorkoutPlan;
import com.gym.app.entity.WorkoutSchedule;
import com.gym.app.repository.DailyExercisePlanRepository;
import com.gym.app.repository.ExerciseRepository;
import com.gym.app.repository.UserWorkoutPlanRepository;
import com.gym.app.repository.WorkoutScheduleRepository;
import com.gym.app.service.UserWorkoutPlanService;
import com.gym.app.service.dto.PlanSelectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserWorkoutPlanServiceImpl implements UserWorkoutPlanService {

    @Autowired
    private UserWorkoutPlanRepository userWorkoutPlanRepository;

    @Autowired
    private WorkoutScheduleRepository workoutScheduleRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private DailyExercisePlanRepository dailyExercisePlanRepository;

    @Override
    public void createWorkoutPlan(PlanSelectionRequest request) throws BaseException{
        List<List<String>> groupSchedule = request.getMuscleGroupSchedule();

        // Flatten the muscle group list for storing
        List<String> flatList = new ArrayList<>();
        for (List<String> dayGroups : groupSchedule) {
            flatList.addAll(dayGroups);
        }

        UserWorkoutPlan plan = new UserWorkoutPlan();
        plan.setLoginId(request.getLoginId());
        Timestamp startDate = DateTimeFormatterUtil.getCurrentTimestampInUTC();
        Timestamp endDate = Timestamp.from(startDate.toInstant().plus(30, ChronoUnit.DAYS));
        plan.setPlanStartDate(startDate);
        plan.setMuscleGroupSchedule(flatList);

        UserWorkoutPlan savedPlan = userWorkoutPlanRepository.save(plan);

        generateWorkoutSchedule(savedPlan, groupSchedule);
    }
    private void generateWorkoutSchedule(UserWorkoutPlan plan, List<List<String>> schedulePattern) {
        int patternSize = schedulePattern.size();
        for (int i = 0; i < 30; i++) {
            LocalDate date = plan.getPlanStartDate().toLocalDateTime().toLocalDate().plusDays(i);
            List<String> todayGroups = schedulePattern.get(i % patternSize);

            WorkoutSchedule schedule = new WorkoutSchedule();
            schedule.setPlanId(plan.getId());
            schedule.setLoginId(plan.getLoginId());
            schedule.setWorkoutDate(date);
            schedule.setDayMuscleGroup(String.join(", ", todayGroups));
            schedule.setCompleted(false);

            WorkoutSchedule savedSchedule = workoutScheduleRepository.save(schedule);

            // Assign 3 exercises per group
            for (String group : todayGroups) {
                List<Exercise> exercises = exerciseRepository.findByMuscleGroup(group);

                exercises.stream().limit(3).forEach(ex -> {
                    DailyExercisePlan exercisePlan = new DailyExercisePlan();
                    exercisePlan.setExerciseName(ex.getExerciseName());
                    exercisePlan.setMuscleGroup(ex.getMuscleGroup());
                    exercisePlan.setSets(3);
                    exercisePlan.setReps(12);
                    exercisePlan.setCompleted(false);
                    exercisePlan.setSchedule(savedSchedule);

                    dailyExercisePlanRepository.save(exercisePlan);
                });
            }
        }
    }
}