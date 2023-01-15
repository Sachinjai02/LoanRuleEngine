package com.lybl.loanruleengine.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDetails {
    private String name;
    private int cibil;
    //age in years
    private float age;
    //monthly in hand salary
    private int salary;
    private List<LoanDetails> existingLoans;
    private List<CreditCard> existingCreditCards;
}
