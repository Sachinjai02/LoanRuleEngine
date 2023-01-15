package com.lybl.loanruleengine.factories;

import com.lybl.loanruleengine.enums.RuleType;
import com.lybl.loanruleengine.strategies.TwoWheelerLoanRuleProcessingStrategy;
import com.lybl.loanruleengine.strategies.PersonalLoanRuleProcessingStrategy;
import com.lybl.loanruleengine.strategies.RuleProcessingStrategy;

public class RuleProcessingStrategyFactory {

    public static RuleProcessingStrategy getRuleProcessor(RuleType ruleType) {
        switch(ruleType) {
            case TWOWHEELERAUTOMOBILE:
                return new TwoWheelerLoanRuleProcessingStrategy();
            case PERSONAL:
                return new PersonalLoanRuleProcessingStrategy();
        }
        throw new RuntimeException("No other rule types supported yet!");
    }
}
