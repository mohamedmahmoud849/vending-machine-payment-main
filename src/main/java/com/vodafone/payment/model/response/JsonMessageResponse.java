package com.vodafone.payment.model.response;

import lombok.Data;

@Data
public abstract class JsonMessageResponse 
{
    protected String message;
    public JsonMessageResponse(String m)
    {
        message = m;
    }
    public JsonMessageResponse(){}
}
