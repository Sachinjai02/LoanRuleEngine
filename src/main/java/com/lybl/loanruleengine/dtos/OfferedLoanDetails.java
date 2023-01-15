package com.lybl.loanruleengine.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OfferedLoanDetails {

    private List<LoanOffer> offers;
    private String message;
    private int cibil;
}
