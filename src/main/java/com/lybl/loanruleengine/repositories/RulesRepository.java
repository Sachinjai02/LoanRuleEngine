package com.lybl.loanruleengine.repositories;

import com.lybl.loanruleengine.enums.RuleType;
import com.lybl.loanruleengine.factories.RuleFactory;
import com.lybl.loanruleengine.models.Rule;
import com.lybl.loanruleengine.strategies.RuleParserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RulesRepository {

    private Date lastCachedRulesTime;
    //default value of 0 ->  meaning no caching
    @Value("${cacheExpiryInMillis:0}")
    private Long cacheExpiryInMillis;

    @Autowired
    private RuleParserStrategy ruleParserStrategy;
    private List<Rule> allRules;

    public List<Rule> refreshRules() {
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
        populateRules(rules);
        
        
        return rules.stream().map(rule -> {
            try {
                Rule ruleInstance = ruleParserStrategy.parseRule(rule.getRuleJson(), rule.getRuleType());
                copyBaseProperties(ruleInstance, rule);
                return ruleInstance;
            } catch(Exception e) {
                //invalid rule; filter them later
                e.printStackTrace();
                return null;
            }
        }).filter( rule -> rule != null).collect(Collectors.toList());


    }

    private void populateRules(List<Rule> rules) {
        Rule rule = new Rule(RuleType.PERSONAL, 1, 1,
                "{\"cibilLowerRange\":851,\"cibilHigherRange\":900,\"salaryLowerRange\":100000," +
                        "\"salaryHigherRange\":2147483647,\"interestRate\":12.5,\"tenure\":3,\"loanAmount\":800000}");
        rules.add(rule);

        rule = new Rule(RuleType.PERSONAL, 1, 2,
                "{\"cibilLowerRange\":800,\"cibilHigherRange\":850,\"salaryLowerRange\":75000," +
                        "\"salaryHigherRange\":2147483647,\"interestRate\":13,\"tenure\":4,\"loanAmount\":600000}");

        rules.add(rule);

        rule = new Rule(RuleType.PERSONAL, 1, 3,
                "{\"cibilLowerRange\":750,\"cibilHigherRange\":799,\"salaryLowerRange\":50000," +
                        "\"salaryHigherRange\":2147483647,\"interestRate\":13.5,\"tenure\":5,\"loanAmount\":500000}");

        rules.add(rule);

    }

    private void copyBaseProperties(Rule child, Rule parent) {
        child.setRuleJson(parent.getRuleJson());
        child.setRuleType(parent.getRuleType());
        child.setId(parent.getId());
        child.setPriority(parent.getPriority());
    }
}
