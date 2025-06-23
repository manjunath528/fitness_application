package com.gym.app.service.badges.evaluator;

import com.gym.app.entity.Badge;
import com.gym.app.entity.UserAccount;
import com.gym.app.repository.UserAccountRepository;
import com.gym.app.service.badges.BadgeCriteriaEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileCompletionEvaluator implements BadgeCriteriaEvaluator {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public boolean isEligible(String loginId, Badge badge) {
        UserAccount userAccount = userAccountRepository.findByLoginId(loginId);
        if (userAccount != null) {
            String personalStatus = userAccount.getPersonal_details_status();
            String healthStatus = userAccount.getHealth_details_status();
            if ("Active".equals(personalStatus) && "Uploaded".equals(healthStatus)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getCriteriaKey() {
        return "profile_completion";
    }
}
