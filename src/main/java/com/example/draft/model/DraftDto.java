package com.example.draft.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DraftDto {

    private Long totalAmount;
    private Long franchiseAmount;
    private Long firstInsuredAmount;
    private Long nonInsuredAmount;
    private Long payableAMount;
    private String nationalCode;

}
