package payment;

import models.User;

public class CreditCardPayment implements Payment {

    @Override
    public void processPayment(User user, double amount) {
        user.withdraw(amount);
        System.out.println("Paying with Credit Card: " + amount);
    }

}
