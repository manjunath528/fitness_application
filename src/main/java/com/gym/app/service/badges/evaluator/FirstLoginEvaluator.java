package com.gym.app.service.badges.evaluator;

import com.gym.app.entity.Badge;
import com.gym.app.repository.UserLoginDataRepository;
import com.gym.app.service.badges.BadgeCriteriaEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstLoginEvaluator implements BadgeCriteriaEvaluator {

    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    @Override
    public boolean isEligible(String loginId, Badge badge) {
        return userLoginDataRepository.countLogins(loginId) == 1;
    }

    @Override
    public String getCriteriaKey() {
        return "first_login";
    }
}
