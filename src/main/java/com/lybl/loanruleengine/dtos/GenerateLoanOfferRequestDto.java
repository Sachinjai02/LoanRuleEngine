package com.lybl.loanruleengine.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GenerateLoanOfferRequestDto {
    private String userName;
    private String panCard;
    //age in years
    private float age;
    //monthly in hand salary
    private Integer salary;
    private List<LoanDetails> existingLoans;
    private List<CreditCard> existingCreditCards;

}
