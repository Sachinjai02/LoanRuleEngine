package com.lybl.loanruleengine.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TwoWheelerLoanRule extends Rule {

    private int cibilLowerRange;
    private int cibilHigherRange;
    private int salaryLowerRange;
    private int salaryHigherRange;

    private boolean existingTwoWheelerLoansAllowed;

    private double interestRate;
    private int tenure;
    private int loanAmount;

}
