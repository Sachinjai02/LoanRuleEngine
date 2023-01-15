package com.lybl.loanruleengine.services;

import com.lybl.loanruleengine.adapter.LoanDetailsAdapter;
import com.lybl.loanruleengine.constants.LoanEngineConstants;
import com.lybl.loanruleengine.dtos.GenerateLoanOfferRequestDto;
import com.lybl.loanruleengine.dtos.LoanOffer;
import com.lybl.loanruleengine.dtos.OfferedLoanDetails;
import com.lybl.loanruleengine.dtos.UserDetails;
import com.lybl.loanruleengine.exception.InvalidPANDetailsException;
import com.lybl.loanruleengine.factories.RuleProcessingStrategyFactory;
import com.lybl.loanruleengine.models.Rule;
import com.lybl.loanruleengine.repositories.RulesRepository;
import com.lybl.loanruleengine.strategies.RuleProcessingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public OfferedLoanDetails generateLoanOffers(GenerateLoanOfferRequestDto requestDto) {
        validateRequestParams(requestDto);
        //Create user details
        UserDetails userDetails = getUserDetails(requestDto);
        userDetails.setCibil(loanDetailsAdapter.getCibilScore(requestDto.getPanCard()));

        List<Rule> rules = this.rulesRepository.refreshRules();
        OfferedLoanDetails offeredLoanDetails = new OfferedLoanDetails();
        List<LoanOffer> loanOffers = rules.stream().map(rule -> {
            RuleProcessingStrategy strategy = RuleProcessingStrategyFactory.getRuleProcessor(rule.getRuleType());
            return strategy.generateOfferIfEligible(rule, userDetails);
        }).filter(loanOffer -> loanOffer != null).collect(Collectors.toList());
        offeredLoanDetails.setOffers(loanOffers);
        offeredLoanDetails.setCibil(userDetails.getCibil());
        offeredLoanDetails.setMessage(String.format(loanOffers.size() == 0 ? LoanEngineConstants.NO_LOAN_OFFER_AVAILABLE
                : LoanEngineConstants.EXCITING_OFFERS_AVAILABLE, userDetails.getName()));
        return offeredLoanDetails;
    }

    private void validateRequestParams(GenerateLoanOfferRequestDto requestDto) {
        String panCard = requestDto.getPanCard();
        if(panCard == null || StringUtils.trimAllWhitespace(panCard).equals("")) {
            throw new InvalidPANDetailsException(LoanEngineConstants.NO_PAN_PROVIDED_EXCEPTION);
        }
    }

    private UserDetails getUserDetails(GenerateLoanOfferRequestDto requestDto) {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(requestDto.getUserName());
        userDetails.setAge(requestDto.getAge());
        userDetails.setSalary(requestDto.getSalary());
        userDetails.setExistingLoans(requestDto.getExistingLoans());
        userDetails.setExistingCreditCards(requestDto.getExistingCreditCards());
        return userDetails;
    }
}
