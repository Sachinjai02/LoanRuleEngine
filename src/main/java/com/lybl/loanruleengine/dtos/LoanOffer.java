package com.lybl.loanruleengine.dtos;

import com.lybl.loanruleengine.enums.LoanType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanOffer {
    private Integer amount;
    private double interestRate;
    //tenure in months
    private Integer tenure;
    private String category;
}
