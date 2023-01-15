package com.lybl.loanruleengine.strategies;

import com.lybl.loanruleengine.enums.RuleType;
import com.lybl.loanruleengine.models.Rule;

public interface RuleParserStrategy {
    public Rule parseRule(String ruleJson, RuleType ruleType);
}
