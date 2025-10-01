package com.company.nextlabs.policygen.policy;

import com.company.nextlabs.policygen.exception.PolicyGenerationException;
import com.company.nextlabs.policygen.exception.ValidationException;
import com.company.nextlabs.policygen.Excel.model.PolicyData;
import com.company.nextlabs.policygen.Excel.model.RuleData;
import com.company.nextlabs.policygen.policy.model.AbacPolicy;

import java.util.List;

public interface PolicyTransformer {
    AbacPolicy transform(PolicyData policyData, List<RuleData> rules) throws PolicyGenerationException;
    String toXmlString(AbacPolicy policy) throws PolicyGenerationException;
    void validate(AbacPolicy policy) throws ValidationException;
}