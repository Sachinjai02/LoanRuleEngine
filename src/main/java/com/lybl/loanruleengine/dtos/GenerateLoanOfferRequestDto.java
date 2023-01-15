package com.lybl.loanruleengine.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GenerateLoanOfferRequestDto {
    private String userName;
    private String panCard;
    private float age;
    private List<LoanDetails> existingLoans;
    private List<CreditCard> existingCreditCards;

}
