package com.lybl.loanruleengine.strategies;

import com.lybl.loanruleengine.dtos.LoanOffer;
import com.lybl.loanruleengine.dtos.UserDetails;
import com.lybl.loanruleengine.models.Rule;


public class TwoWheelerLoanRuleProcessingStrategy implements RuleProcessingStrategy {
    @Override
    public LoanOffer generateOfferIfEligible(Rule rule, UserDetails userDetails) {
        return null;
    }
}
