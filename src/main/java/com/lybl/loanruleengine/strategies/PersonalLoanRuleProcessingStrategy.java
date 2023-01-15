package com.lybl.loanruleengine.strategies;

import com.lybl.loanruleengine.dtos.LoanOffer;
import com.lybl.loanruleengine.dtos.UserDetails;
import com.lybl.loanruleengine.models.PersonalLoanRule;
import com.lybl.loanruleengine.models.Rule;

public class PersonalLoanRuleProcessingStrategy implements RuleProcessingStrategy {
    @Override
    public LoanOffer generateOfferIfEligible(Rule rule, UserDetails userDetails) {
        PersonalLoanRule personalLoanRule = (PersonalLoanRule) rule;
        int cibil = userDetails.getCibil();
        int salary = userDetails.getSalary();

        LoanOffer loanOffer = null;

        if(cibil >= personalLoanRule.getCibilLowerRange() && cibil <= personalLoanRule.getCibilHigherRange()
         && salary >= personalLoanRule.getSalaryLowerRange() && salary <= personalLoanRule.getSalaryHigherRange()) {
            loanOffer = new LoanOffer();
            loanOffer.setAmount(personalLoanRule.getLoanAmount());
            loanOffer.setCategory(rule.getRuleType().name());
            loanOffer.setInterestRate(personalLoanRule.getInterestRate());
            loanOffer.setTenure(personalLoanRule.getTenure());
            loanOffer.setBankName(personalLoanRule.getBankName());
        }
        return loanOffer;
    }
}
