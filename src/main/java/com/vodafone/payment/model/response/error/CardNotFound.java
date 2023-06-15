package com.vodafone.payment.model.response.error;

import com.vodafone.payment.model.response.JsonMessageResponse;

public class CardNotFound extends JsonMessageResponse{
    public CardNotFound(String m)
    {
        super(m);
    }
    public CardNotFound()
    {
        message = "Card not found, cannot proceed with payment process";
    }
}
