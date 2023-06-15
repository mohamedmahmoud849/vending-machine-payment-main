package com.vodafone.payment.model.response.error;

import com.vodafone.payment.model.response.JsonMessageResponse;

public class InsufficientBalance extends JsonMessageResponse{
    public InsufficientBalance(String m)
    {
        super(m);
    }
    public InsufficientBalance()
    {
        message = "Insufficient Balance, cannot proceed with payment process";
    }
}
