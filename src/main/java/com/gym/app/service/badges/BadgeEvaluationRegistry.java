package com.gym.app.service.badges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BadgeEvaluationRegistry {

    private final Map<String, BadgeCriteriaEvaluator> evaluators = new HashMap<>();

    @Autowired
    public BadgeEvaluationRegistry(List<BadgeCriteriaEvaluator> evaluatorList) {
        for (BadgeCriteriaEvaluator evaluator : evaluatorList) {
            evaluators.put(evaluator.getCriteriaKey(), evaluator);
        }
    }
    public Optional<BadgeCriteriaEvaluator> getEvaluator(String criteria) {
        return Optional.ofNullable(evaluators.get(criteria));
    }
}
