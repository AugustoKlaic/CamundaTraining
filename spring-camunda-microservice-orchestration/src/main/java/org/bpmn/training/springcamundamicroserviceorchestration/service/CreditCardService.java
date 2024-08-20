package org.bpmn.training.springcamundamicroserviceorchestration.service;

import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    public String chargeCreditCard(final String reference,
                                   final Double amount,
                                   final String cardNumber,
                                   final String cardExpiry,
                                   final String cardCVC) {
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
