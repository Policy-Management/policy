package com.policy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class PolicyEntity {
    @Id
    private String id;
    private String policyName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    private long durationInYears;
    private String companyName;
    private long amount;
    private String policyType;
    private String userTypes;
    private int termsPerYear;
    private long termAmount;
    private int interest;
}