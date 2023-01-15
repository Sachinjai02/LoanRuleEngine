package com.lybl.loanruleengine.models;

import com.lybl.loanruleengine.enums.RuleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    private RuleType ruleType;
    private Integer id;
    private Integer priority;
    private String ruleJson;

}
