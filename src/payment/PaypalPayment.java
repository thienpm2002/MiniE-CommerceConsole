package payment;

import models.User;

public class PaypalPayment implements Payment {

    @Override
    public void processPayment(User user, double amount) {
        user.withdraw(amount);
        System.out.println("Paying with PayPal: " + amount);
    }
}
