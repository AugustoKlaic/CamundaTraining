package org.bpmn.training.springcamundamicroserviceorchestration.service;

import org.bpmn.training.springcamundamicroserviceorchestration.exception.CreditCardServiceException;
import org.bpmn.training.springcamundamicroserviceorchestration.exception.InvalidCreditCardException;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private static final int CARD_EXPIRY_DATE_LENGTH = 7;

    public String chargeCreditCard(final String reference,
                                   final Double amount,
                                   final String cardNumber,
                                   final String cardExpiry,
                                   final String cardCVC) {

        // simulates a credit card failure
        if (cardNumber.equalsIgnoreCase("error")) {
            throw new CreditCardServiceException();
        }

        // simulates expired card failure
        if(cardExpiry.length() < CARD_EXPIRY_DATE_LENGTH) {
            throw new InvalidCreditCardException();
        }

        System.out.println("transaction: " + reference);
        System.out.println("cardNumber: " + cardNumber);
        System.out.println("cardExpiry: " + cardExpiry);
        System.out.println("cardCVC: " + cardCVC);
        System.out.println("amount: " + amount);

        String confirmation = String.valueOf(System.currentTimeMillis());
        System.out.println("Successful transaction: " + confirmation);
        return confirmation;
    }
}
