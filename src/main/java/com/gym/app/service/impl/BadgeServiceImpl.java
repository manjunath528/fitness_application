package com.gym.app.service.impl;

import com.gym.app.baseframework.exception.BaseException;
import com.gym.app.common.Constants;
import com.gym.app.common.DateTimeFormatterUtil;
import com.gym.app.entity.Badge;
import com.gym.app.entity.UserBadge;
import com.gym.app.entity.WorkoutSchedule;
import com.gym.app.repository.BadgeRepository;
import com.gym.app.repository.UserBadgeRepository;
import com.gym.app.repository.WorkoutScheduleRepository;
import com.gym.app.service.BadgeService;
import com.gym.app.service.badges.BadgeEvaluationRegistry;
import com.gym.app.service.dto.WorkoutScheduleCompletedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService {
    private static Logger logger = LoggerFactory.getLogger(BadgeServiceImpl.class);
    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    @Autowired
    private WorkoutScheduleRepository workoutScheduleRepository;

    @Autowired
    private BadgeEvaluationRegistry badgeEvaluationRegistry;



    @Override
    public void evaluateAndAssignBadges(String loginId) throws BaseException {
        logger.info("evaluation of badges received for->{}", loginId);
        List<Badge> allBadges = badgeRepository.findAll();
        for (Badge badge : allBadges) {
            logger.info("badge evaluation of {} for->{} received", badge, loginId);
            if (!userBadgeRepository.existsByUserLoginIdAndBadgeId(loginId, badge.getId())) {
                logger.info("user doesn't have badge->{}",badge);
                badgeEvaluationRegistry.getEvaluator(badge.getCriteria())
                        .filter(evaluator -> evaluator.isEligible(loginId, badge))
                        .ifPresent(evaluator -> {
                            UserBadge userBadge = new UserBadge();
                            userBadge.setBadge(badge);
                            userBadge.setLoginId(loginId);
                            userBadge.setAwardedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
                            userBadgeRepository.save(userBadge);
                            logger.info("user badge evaluation for badge->{} successfully done",badge);
                        });
            }
        }
    }

    @Override
    public WorkoutScheduleCompletedResponse completeWorkout(Long workoutScheduleId) throws BaseException {
        logger.info("workoutScheduleId has successfully received:->{}", workoutScheduleId);
        WorkoutSchedule schedule = workoutScheduleRepository.findById(workoutScheduleId)
                .orElseThrow(() -> new BaseException("No workoutSchedule found"));
        schedule.setCompleted(true);
        //schedule.setCompletedAt(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        workoutScheduleRepository.save(schedule);
        logger.info("workoutScheduleId has successfully updated->{}",workoutScheduleId);
        evaluateAndAssignBadges(schedule.getLoginId());
        logger.info("workoutScheduleId has successfully evaluated->{}",workoutScheduleId);
        WorkoutScheduleCompletedResponse workoutScheduleCompletedResponse = new WorkoutScheduleCompletedResponse();
        workoutScheduleCompletedResponse.setWorkoutScheduleId(workoutScheduleId);
        workoutScheduleCompletedResponse.setWorkoutScheduleStatus(Constants.STATUS_COMPLETED);
        logger.info("workoutScheduleResponse has successfully responded->{}",workoutScheduleId);
        return workoutScheduleCompletedResponse;
    }
}
