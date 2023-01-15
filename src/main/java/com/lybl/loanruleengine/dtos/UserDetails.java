package com.lybl.loanruleengine.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDetails {

    private int cibil;
    //age in years
    private float age;
    //monthly in hand salary
    private int salary;
    private List<LoanDetails> existingLoans;
    private List<CreditCard> existingCreditCards;
}
