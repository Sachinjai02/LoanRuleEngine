package com.lybl.loanruleengine.factories;

import com.lybl.loanruleengine.enums.RuleType;
import com.lybl.loanruleengine.models.PersonalLoanRule;
import com.lybl.loanruleengine.models.Rule;
import com.lybl.loanruleengine.models.TwoWheelerLoanRule;
import com.lybl.loanruleengine.strategies.PersonalLoanRuleProcessingStrategy;

public class RuleFactory {

    public static Class<? extends Rule> getRuleInstance(RuleType ruleType) {
        switch(ruleType) {
            case TWOWHEELERAUTOMOBILE:
                return TwoWheelerLoanRule.class;
            case PERSONAL:
                return PersonalLoanRule.class;
        }
        throw new RuntimeException("No other rule types supported yet!");
    }
}
