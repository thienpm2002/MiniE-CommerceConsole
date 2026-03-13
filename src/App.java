import java.util.Scanner;

import exceptions.UserExistException;
import exceptions.UserNotFoundException;
import models.User;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
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
                            System.out.println("2. View Products");
                            System.out.println("3. Add to Cart");
                            System.out.println("4. View Cart");
                            System.out.println("5. Checkout");
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
                                    // Code to view products
                                    break;
                                case 3:
                                    // Code to add to cart
                                    break;
                                case 4:
                                    // Code to view cart
                                    break;
                                case 5:
                                    // Code to checkout
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
