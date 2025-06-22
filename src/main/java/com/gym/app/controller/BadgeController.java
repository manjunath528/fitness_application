package com.gym.app.controller;


import com.gym.app.baseframework.controller.BaseController;
import com.gym.app.baseframework.exception.BaseException;
import com.gym.app.service.BadgeService;
import com.gym.app.service.dto.WorkoutScheduleCompletedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRoute.BASE_MAPPING_API_V1)
@CrossOrigin(origins = "*")
public class BadgeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BadgeController.class);

    @Autowired
    BadgeService badgeService;

    @PostMapping(value = ApiRoute.WORKOUT_SCHEDULE_UPDATE)
    public ResponseEntity<WorkoutScheduleCompletedResponse> completeWorkout(@RequestParam(value = "workoutScheduleId") Long workoutScheduleId) throws BaseException {
        logger.info("workoutScheduleId: Received");
        return new ResponseEntity<>(badgeService.completeWorkout(workoutScheduleId), HttpStatus.OK);
    }
}
