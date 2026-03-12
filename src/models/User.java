package models;

public class User {
    private Long id;
    private String name;
    private String email;
    private double balance;

    public User(Long id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (balance < amount) {
            throw new IllegalStateException("Balance cannot be negative");
        }
        balance -= amount;
    }
}
