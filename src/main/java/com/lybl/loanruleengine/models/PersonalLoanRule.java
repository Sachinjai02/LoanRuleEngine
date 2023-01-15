package com.lybl.loanruleengine.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalLoanRule extends Rule {
    private int cibilLowerRange;
    private int cibilHigherRange;
    private int salaryLowerRange;
    private int salaryHigherRange;

    private double interestRate;
    private int tenure;
    private int loanAmount;
    private String bankName;

}
