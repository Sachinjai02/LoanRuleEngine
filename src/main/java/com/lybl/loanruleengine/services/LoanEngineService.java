package com.lybl.loanruleengine.services;

import com.lybl.loanruleengine.dtos.GenerateLoanOfferRequestDto;
import com.lybl.loanruleengine.dtos.GenerateLoanOfferResponseDto;
import com.lybl.loanruleengine.dtos.LoanOffer;
import com.lybl.loanruleengine.models.Rule;
import com.lybl.loanruleengine.repositories.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanEngineService {

    private RulesRepository rulesRepository;
    @Autowired
    public LoanEngineService(RulesRepository rulesRepository){
        this.rulesRepository = rulesRepository;
    }

    public List<LoanOffer> generateLoanOffers(GenerateLoanOfferRequestDto requestDto) {
        List<Rule> rules = this.rulesRepository.refreshRules();

        return null;
    }
}
