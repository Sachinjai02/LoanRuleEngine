package com.lybl.loanruleengine.adapter;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 Assume this API uses 3rd party API of PayTM and helps us get the Cibil score

 */
@Component("payTMAdapter")
public class PayTMLoanDetailsAdapter implements LoanDetailsAdapter{

    @Override
    public Integer getCibilScore(String panNumber) {
        return ThreadLocalRandom.current().nextInt(650, 900 + 1);
    }
}
