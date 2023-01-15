package com.lybl.loanruleengine.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCard {
    private String cardNumber;
    private String bankName;
}
