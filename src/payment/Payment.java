package payment;

import models.User;

public interface Payment {
    void processPayment(User user, double amount);
}
