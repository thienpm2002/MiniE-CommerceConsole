package services;

import exceptions.UserExistException;
import exceptions.UserNotFoundException;
import models.User;
import repositories.UserRepository;
import utils.UserUtil;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void registerUser(String name, String email, double balance) {
        if (!UserUtil.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name. Name must be between 2 and 100 characters.");
        }
        if (!UserUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new UserExistException("Email " + email + " already exists.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        User user = new User(name, email, balance);
        userRepository.save(user);
    }

    public User loginUser(String email) {
        if (!UserUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found."));
    }

    public void depositMoney(User user, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        user.deposit(amount);
    }
}
