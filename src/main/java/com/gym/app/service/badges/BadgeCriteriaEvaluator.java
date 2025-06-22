package com.gym.app.service.badges;

import com.gym.app.entity.Badge;

public interface BadgeCriteriaEvaluator {
    boolean isEligible(String loginId, Badge badge);
    String getCriteriaKey();
}