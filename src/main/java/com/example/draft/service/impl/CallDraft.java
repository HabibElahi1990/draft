package com.example.draft.service.impl;

import com.example.draft.model.Draft;

import static com.example.draft.helper.GeneralConstraint.MAX_AMOUNT;
import static com.example.draft.helper.GeneralConstraint.ZERO_AMOUNT;

public class CallDraft {

    public void checkAmounts(Long amount,String type){
        if (type.equals("totalAmount")){
            if (amount<=ZERO_AMOUNT){
                throw new IllegalArgumentException("total is invalid number");
            }
        }else {
            if (amount<ZERO_AMOUNT){
                throw new IllegalArgumentException("total is invalid number");
            }
        }
        if (amount>MAX_AMOUNT){
            throw new IllegalArgumentException("max total is 999999 number");
        }
    }

    private void validateAmount(Draft draft){
        checkAmounts(draft.getTotalAmount(),"totalAmount");
        checkAmounts(draft.getFranchiseAmount(),"");
        checkAmounts(draft.getFirstInsuredAmount(),"");
        checkAmounts(draft.getNonInsuredAmount(),"");
    }

    public Long calcAmount(Draft draft) {
        validateAmount(draft);
        long payableAmount=0L;
        Long mainAmount=draft.getTotalAmount()-draft.getNonInsuredAmount();
        if (draft.getFirstInsuredAmount()>0) {
            if (draft.getFirstInsuredAmount() >= draft.getFranchiseAmount()) {
                payableAmount = mainAmount - draft.getFirstInsuredAmount();
            } else {
                payableAmount = mainAmount - draft.getFranchiseAmount();
            }
        }else if (draft.getFranchiseAmount()>0){
            payableAmount=mainAmount-draft.getFranchiseAmount();
        }else {
            payableAmount=mainAmount;
        }
        return payableAmount;
    }
}
