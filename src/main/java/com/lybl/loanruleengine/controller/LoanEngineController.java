package com.lybl.loanruleengine.controller;

import com.lybl.loanruleengine.LoanEngineConstants;
import com.lybl.loanruleengine.dtos.GenerateLoanOfferRequestDto;
import com.lybl.loanruleengine.dtos.GenerateLoanOfferResponseDto;
import com.lybl.loanruleengine.dtos.LoanOffer;
import com.lybl.loanruleengine.services.LoanEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/loan")
public class LoanEngineController {

    private LoanEngineService loanEngineService;

    @Autowired
    public LoanEngineController(LoanEngineService loanEngineService) {
        this.loanEngineService = loanEngineService;
    }

    @PostMapping("/offers")
    public ResponseEntity<GenerateLoanOfferResponseDto> generateLoanOffers(@RequestBody GenerateLoanOfferRequestDto requestDto) {
        GenerateLoanOfferResponseDto responseDto = new GenerateLoanOfferResponseDto();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            List<LoanOffer> offers = loanEngineService.generateLoanOffers(requestDto);
            responseDto.setOffers(offers);
            responseDto.setMessage(offers.size() > 0 ? LoanEngineConstants.NO_LOAN_OFFER_AVAILABLE : LoanEngineConstants.EXCITING_OFFERS_AVAILABLE);
        }catch(Exception e) {
            e.printStackTrace();
            responseDto.setErrorMessage(e.getMessage());
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(responseDto, responseStatus);
    }
}
