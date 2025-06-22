package com.gym.app.service.badges.evaluator;
import com.gym.app.entity.Badge;
import com.gym.app.repository.UserLoginDataRepository;
import com.gym.app.service.badges.BadgeCriteriaEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginStreakEvaluator implements BadgeCriteriaEvaluator {

    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    @Override
    public boolean isEligible(String loginId, Badge badge) {
        List<Timestamp> loginTimestamps = userLoginDataRepository.findRecentLoginTimestamps(loginId);

        if (loginTimestamps.isEmpty()) return false;

        // Convert timestamps to LocalDates
        Set<LocalDate> loginDates = loginTimestamps.stream()
                .map(ts -> ts.toLocalDateTime().toLocalDate())
                .collect(Collectors.toSet());

        LocalDate today = LocalDate.now();
        int streak = 0;

        // Check each day going backwards
        for (int i = 0; i < badge.getTargetValue(); i++) {
            LocalDate checkDate = today.minusDays(i);
            if (loginDates.contains(checkDate)) {
                streak++;
            } else {
                break; // streak ends
            }
        }

        return streak >= badge.getTargetValue();
    }

    @Override
    public String getCriteriaKey() {
        return "login_streak";
    }
}
