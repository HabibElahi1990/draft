package com.example.draft;

import com.example.draft.model.Draft;
import com.example.draft.service.impl.CallDraft;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DraftApplicationTests {

    private CallDraft callDraft;

    @BeforeEach
    public void initial(){
        callDraft=new CallDraft();
    }

    @ParameterizedTest
    @ValueSource(longs = {0,-1,1000000})
    public void invalidTotalAmount(Long totalAmount){
        Assertions.assertThrows(IllegalArgumentException.class,()->callDraft.checkAmounts(totalAmount,"totalAmount"));
    }

    @ParameterizedTest
    @ValueSource(longs = {-1,1000000})
    public void checkAnotherAmounts(Long amount){
        Assertions.assertThrows(IllegalArgumentException.class,()->callDraft.checkAmounts(amount,""));
    }

    @Test
    public void calcValidAmountDraft(){
       /*
       * x=totalAmount-nonInsuredAmount
       * firstInsuredAmount>0 -> franchiseAmount >firstInsuredAmount ----> y= x-franchiseAmount else y=x-firstInsuredAmount
       * else franchiseAmount > 0 -> y=x-franchiseAmount;
       * y=x;
       * 10000-1500=8500
       * 4000>0-> 0>400 ->>>>>>>>>>>>y= 8500-4000=4500
       * *********************************************
       * 10000-1500=8500
       * 4000>0-> 0>400 ->>>>>>>>>>>>y= 8500-4000=4500
       *
       * */
        Draft draft=Draft.builder()
                .totalAmount(10000L)
                .firstInsuredAmount(4000L)
                .nonInsuredAmount(1500L)
                .franchiseAmount(0L).build();
        Assertions.assertEquals(4500L,callDraft.calcAmount(draft));

        draft=Draft.builder()
                .totalAmount(10000L)
                .firstInsuredAmount(0L)
                .nonInsuredAmount(1500L)
                .franchiseAmount(0L).build();
        Assertions.assertEquals(8500L,callDraft.calcAmount(draft));

        draft=Draft.builder()
                .totalAmount(10000L)
                .firstInsuredAmount(4000L)
                .nonInsuredAmount(1500L)
                .franchiseAmount(5000L).build();
        Assertions.assertEquals(3500L,callDraft.calcAmount(draft));
    }

    @Test
    public void calcInValidAmountDraft(){
        Draft draft=Draft.builder()
                .totalAmount(10000L)
                .firstInsuredAmount(4000L)
                .nonInsuredAmount(-1500L)
                .franchiseAmount(0L).build();
        Assertions.assertThrows(IllegalArgumentException.class,()->callDraft.calcAmount(draft));
    }



}
