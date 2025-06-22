package com.gym.app.controller;

import com.gym.app.baseframework.exception.BaseException;
import com.gym.app.service.UserWorkoutPlanService;
import com.gym.app.service.dto.PlanSelectionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiRoute.BASE_MAPPING_API_V1)
@CrossOrigin(origins = "*")
public class UserWorkoutPlanController {
    private static final Logger logger = LoggerFactory.getLogger(UserWorkoutPlanController.class);

    @Autowired
    private UserWorkoutPlanService workoutPlanService;

    @PostMapping(value = ApiRoute.CREATE_WORKOUT_PLAN)
    public ResponseEntity<String> createPlan(@RequestBody PlanSelectionRequest request) throws BaseException {
        logger.info("PlanSelectionRequest :Received");
        workoutPlanService.createWorkoutPlan(request);
        return ResponseEntity.ok("Workout Plan Created Successfully");
    }
}
