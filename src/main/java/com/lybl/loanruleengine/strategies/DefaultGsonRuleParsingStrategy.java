package com.lybl.loanruleengine.strategies;

import com.google.gson.Gson;
import com.lybl.loanruleengine.enums.RuleType;
import com.lybl.loanruleengine.factories.RuleFactory;
import com.lybl.loanruleengine.models.Rule;
import org.springframework.stereotype.Service;

@Service
public class DefaultGsonRuleParsingStrategy implements RuleParserStrategy {

    private RuleFactory ruleFactory;

    @Override
    public Rule parseRule(String ruleJson, RuleType ruleType) {
        Class<? extends Rule> ruleInstanceClass = RuleFactory.getRuleInstance(ruleType);
        Gson gson = new Gson();
        return gson.fromJson(ruleJson, ruleInstanceClass);
    }
}
