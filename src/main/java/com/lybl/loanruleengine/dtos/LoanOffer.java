package com.lybl.loanruleengine.dtos;

import com.lybl.loanruleengine.enums.LoanType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanOffer {
    private int amount;
    private double interestRate;
    //tenure in months
    private int tenure;
    private String category;
    private int cibil;
}
