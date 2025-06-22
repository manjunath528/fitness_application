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
        if(userAccount.getPersonal_details_status().equals("Active") && userAccount.getPersonal_details_status().equals("Uploaded")){
            return true;
        }
        return false;
    }
    @Override
    public String getCriteriaKey() {
        return "profile_complete";
    }
}
