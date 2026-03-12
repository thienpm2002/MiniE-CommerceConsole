package services;

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
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        User user = new User(name, email, balance);
        userRepository.save(user);
    }
}
