package com.lybl.loanruleengine.repositories;

import com.lybl.loanruleengine.models.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RulesRepository {

    private Date lastCachedRulesTime;
    //default value of 0 ->  meaning no caching
    @Value("cacheExpiryInMillis:0")
    private Long cacheExpiryInMillis;

    private List<Rule> allRules;

    private List<Rule> fetchRules() {
        Date currentTime = new Date();
        if(lastCachedRulesTime == null || currentTime.getTime() - lastCachedRulesTime.getTime() >= cacheExpiryInMillis) {
            //refresh the rules
            allRules = fetchRulesFromCentralLocation();
        }
        return allRules;

    }

    /*
    This is a static list of rules but expectation is that these rules would be populated from an external config server
    or a database
     */
    private List<Rule> fetchRulesFromCentralLocation() {
        List<Rule> rules = new ArrayList<>();

        return rules;
    }
}
