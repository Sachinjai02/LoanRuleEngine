package com.lybl.loanruleengine.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GenerateLoanOfferResponseDto extends BaseResponseDto {
    private List<LoanOffer> offers;
    private String message;

}
