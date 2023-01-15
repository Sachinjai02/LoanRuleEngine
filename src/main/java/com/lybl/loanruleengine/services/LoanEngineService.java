package com.lybl.loanruleengine.services;

import com.lybl.loanruleengine.adapter.LoanDetailsAdapter;
import com.lybl.loanruleengine.dtos.GenerateLoanOfferRequestDto;
import com.lybl.loanruleengine.dtos.LoanOffer;
import com.lybl.loanruleengine.dtos.UserDetails;
import com.lybl.loanruleengine.factories.RuleProcessingStrategyFactory;
import com.lybl.loanruleengine.models.Rule;
import com.lybl.loanruleengine.repositories.RulesRepository;
import com.lybl.loanruleengine.strategies.RuleProcessingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanEngineService {

    private LoanDetailsAdapter loanDetailsAdapter;
    private RulesRepository rulesRepository;

    @Autowired
    public LoanEngineService(RulesRepository rulesRepository, LoanDetailsAdapter loanDetailsAdapter){
        this.rulesRepository = rulesRepository;
        this.loanDetailsAdapter = loanDetailsAdapter;
    }

    public List<LoanOffer> generateLoanOffers(GenerateLoanOfferRequestDto requestDto) {
        //Create user details
        UserDetails userDetails = getUserDetails(requestDto);
        userDetails.setCibil(loanDetailsAdapter.getCibilScore(requestDto.getPanCard()));

        List<Rule> rules = this.rulesRepository.refreshRules();

        return rules.stream().map(rule -> {
            RuleProcessingStrategy strategy = RuleProcessingStrategyFactory.getRuleProcessor(rule.getRuleType());
            return strategy.generateOfferIfEligible(rule, userDetails);
        }).filter(loanOffer -> loanOffer != null).collect(Collectors.toList());
    }

    private UserDetails getUserDetails(GenerateLoanOfferRequestDto requestDto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setAge(requestDto.getAge());
        userDetails.setSalary(requestDto.getSalary());
        userDetails.setExistingLoans(requestDto.getExistingLoans());
        userDetails.setExistingCreditCards(requestDto.getExistingCreditCards());
        return userDetails;
    }
}
