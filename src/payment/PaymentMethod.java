package payment;

public class PaymentMethod {
    public static Payment getPaymentMethod(int choice) {
        return switch (choice) {
            case 1 -> new CreditCardPayment();
            case 2 -> new PaypalPayment();
            case 3 -> new WalletPayment();
            default -> null;
        };
    }
}
