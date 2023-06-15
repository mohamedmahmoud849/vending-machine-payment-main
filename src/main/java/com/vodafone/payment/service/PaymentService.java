package com.vodafone.payment.service;

import com.vodafone.payment.model.CreditCard;
import com.vodafone.payment.model.RequestData;
import com.vodafone.payment.model.response.error.CardNotFound;
import com.vodafone.payment.model.response.error.InsufficientBalance;
import com.vodafone.payment.model.response.success.PaymentSucceeded;
import com.vodafone.payment.repository.CardPaymentRepo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pay")
public class PaymentService 
{
    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkCreditCard(RequestData requestData) {
        
        // Retrieve the updated credit card information
        CardPaymentRepo cardPaymentRepo = new CardPaymentRepo();
        CreditCard creditCard = cardPaymentRepo.getCardInformation(requestData.getCardNumber());
        
        if(creditCard == null)
        {
            return Response.status(404).entity(new CardNotFound()).build();
        }

        // Card information exists in the database
        int currentBalance = creditCard.getBalance();
        if (currentBalance < requestData.getAmountToBePaid()) 
        {
           // Insufficient balance
            return Response.status(406).entity(new InsufficientBalance()).build();
        }
        /*int newBalance = currentBalance - requestData.getAmountToBePaid();

        cardPaymentRepo.modifyCardBalance(requestData.getCardNumber(), newBalance);*/
        return Response.status(200).entity(new PaymentSucceeded("There's Enough Balance")).build();
    }

    @POST
    @Path("/consume")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response payWithCreditCard(RequestData requestData) {

        // Retrieve the updated credit card information
        CardPaymentRepo cardPaymentRepo = new CardPaymentRepo();
        CreditCard creditCard = cardPaymentRepo.getCardInformation(requestData.getCardNumber());

        if(creditCard == null)
        {
            return Response.status(404).entity(new CardNotFound()).build();
        }

        // Card information exists in the database
        int currentBalance = creditCard.getBalance();
        if (currentBalance < requestData.getAmountToBePaid())
        {
            // Insufficient balance
            return Response.status(406).entity(new InsufficientBalance()).build();
        }
        int newBalance = currentBalance - requestData.getAmountToBePaid();

        cardPaymentRepo.modifyCardBalance(requestData.getCardNumber(), newBalance);
        return Response.status(200).entity(new PaymentSucceeded()).build();
    }
}
