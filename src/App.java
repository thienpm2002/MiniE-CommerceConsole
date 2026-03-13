import java.util.Scanner;

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
                        System.out.println("Error: " + e.getMessage());
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
