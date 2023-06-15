package com.vodafone.payment.model.response.success;

import com.vodafone.payment.model.response.JsonMessageResponse;

public class PaymentSucceeded extends JsonMessageResponse
{
    public PaymentSucceeded(String m)
    {
        super(m);
    }
    public PaymentSucceeded()
    {
        message = "Transaction Succeeded";
    }
}
