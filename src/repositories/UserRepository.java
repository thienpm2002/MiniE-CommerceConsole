package repositories;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import models.User;

public class UserRepository implements RepositoryInterface<Long, User> {
    private Map<Long, User> userDatabase = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public void save(User entity) {
        entity.setId(currentId++);
        userDatabase.put(entity.getId(), entity);
    }

    @Override
    public User findById(Long id) {
        return userDatabase.get(id);
    }

    // Find all users with a specific condition (name contains a keyword)
    @Override
    public List<User> findAll(String condition) {
        return userDatabase.values().stream()
                .filter(user -> user.getName().toLowerCase().contains(condition.toLowerCase()))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        userDatabase.remove(id);
    }
}
