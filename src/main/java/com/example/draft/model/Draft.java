package com.example.draft.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DRAFT_TBL")
public class Draft {

    @Id
    @GeneratedValue
    private Integer id;
    private Long totalAmount;
    private Long franchiseAmount;
    private Long firstInsuredAmount;
    private Long nonInsuredAmount;
    private Long payableAMount;
    private Integer personId;
    private Integer status;
}
