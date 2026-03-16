
import java.util.Scanner;

import exceptions.ProductNotFoundException;
import exceptions.UserExistException;
import exceptions.UserNotFoundException;
import models.User;
import payment.CreditCardPayment;
import payment.Payment;
import payment.PaypalPayment;
import payment.WalletPayment;
import services.CartService;
import services.OrderService;
import services.ProductService;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        CartService cartService = new CartService(productService);
        OrderService orderService = new OrderService();
        int choice;
        do {
            System.out.println("\nWelcome to the Mini E-Commerce Application!");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter your email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter your initial balance: ");
                    double balance = sc.nextDouble();
                    try {
                        userService.registerUser(name, email, balance);
                        System.out.println("User registered successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (UserExistException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter your email: ");
                    String loginEmail = sc.nextLine();
                    try {
                        User user = userService.loginUser(loginEmail);
                        System.out.println("Login successful!");
                        System.out
                                .println("Hi, " + user.getName() + "! Your current balance is: $" + user.getBalance());
                        int loginchoice;
                        do {
                            System.out.println("1. Deposit money");
                            System.out.println("2. Add product");
                            System.out.println("3. View Products");
                            System.out.println("4. Add to Cart");
                            System.out.println("5. Remove from Cart");
                            System.out.println("6. View Cart");
                            System.out.println("7. Checkout");
                            System.out.println("8. View Orders");
                            System.out.println("9. Pay Order");
                            System.out.println("10. Cancel Order");
                            System.out.println("11. Ship Order");
                            System.out.println("0. Logout");
                            System.out.print("Enter your choice: ");
                            loginchoice = sc.nextInt();
                            sc.nextLine();

                            switch (loginchoice) {

                                case 1:
                                    // Code to deposit money
                                    System.out.print("Enter amount to deposit: ");
                                    double amount = sc.nextDouble();
                                    sc.nextLine();
                                    try {
                                        userService.depositMoney(user, amount);
                                        System.out.println(
                                                "Deposit successful! Your new balance is: $" + user.getBalance());
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    // Code to add product
                                    System.out.print("Enter product name: ");
                                    String productName = sc.nextLine();
                                    System.out.print("Enter product price: ");
                                    double productPrice = sc.nextDouble();
                                    System.out.print("Enter product stock: ");
                                    int productStock = sc.nextInt();
                                    sc.nextLine();
                                    try {
                                        productService.addProduct(productName, productPrice, productStock);
                                        System.out.println("Product added successfully!");
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 3:
                                    // Code to view products
                                    System.out.println("Available Products:");
                                    productService.viewProducts();
                                    break;
                                case 4:
                                    // Code to add to cart
                                    System.out.print("Enter product ID to add to cart: ");
                                    Long productId = sc.nextLong();
                                    System.out.print("Enter quantity: ");
                                    int quantity = sc.nextInt();
                                    sc.nextLine();
                                    try {
                                        cartService.addToCart(productId, quantity);
                                        System.out.println("Product added to cart successfully!");
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    } catch (IllegalStateException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    // Code to remove from cart
                                    System.out.print("Enter product ID to remove from cart: ");
                                    Long removeProductId = sc.nextLong();
                                    sc.nextLine();
                                    try {
                                        cartService.removeFromCart(removeProductId);
                                        System.out.println("Product removed from cart successfully!");
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    } catch (ProductNotFoundException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 6:
                                    // Code to view cart
                                    System.out.println("Your Cart:");
                                    cartService.viewCart();
                                    break;
                                case 7:
                                    // Code to checkout
                                    try {
                                        orderService.checkOut(user, cartService.getCartItems());
                                        System.out.println("Checkout successful! Your order has been placed.");
                                        cartService.clearCart();
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 8:
                                    // Code to view orders
                                    System.out.println("Your Orders:");
                                    orderService.viewOrders();
                                    break;
                                case 9:
                                    // Code to pay order
                                    System.out.println("Enter order id: ");
                                    Long orderId = sc.nextLong();
                                    sc.nextLine();
                                    System.out.println("Enter pay method");
                                    System.out.println("1. Credit Card");
                                    System.out.println("2. Paypal Payment");
                                    System.out.println("3. Wallet Payment");
                                    System.out.println("0. Cancel");
                                    System.out.println("Enter choice: ");
                                    int choiceMethod = sc.nextInt();
                                    sc.nextLine();
                                    switch (choiceMethod) {
                                        case 1:
                                            try {
                                                Payment creditCard = new CreditCardPayment();
                                                orderService.payOrder(orderId, creditCard);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            } catch (IllegalStateException s) {
                                                System.out.println(s.getMessage());
                                            }
                                            break;
                                        case 2:
                                            try {
                                                Payment paypal = new PaypalPayment();
                                                orderService.payOrder(orderId, paypal);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            } catch (IllegalStateException s) {
                                                System.out.println(s.getMessage());
                                            }
                                            break;
                                        case 3:
                                            try {
                                                Payment wallet = new WalletPayment();
                                                orderService.payOrder(orderId, wallet);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println(e.getMessage());
                                            } catch (IllegalStateException s) {
                                                System.out.println(s.getMessage());
                                            }
                                            break;
                                        case 0:
                                            System.out.println("Cancel payment");
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                    break;
                                case 10:
                                    // Code to cancel order
                                    break;
                                case 11:
                                    // Code to ship order
                                    break;
                                case 0:
                                    System.out.println("Logged out successfully!");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        } while (loginchoice != 0);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Thank you for using the Mini E-Commerce Application!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
