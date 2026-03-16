package repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Order;

public class OrderRepository implements RepositoryInterface<Long, Order> {
    private Map<Long, Order> orders = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        orders.remove(id);

    }

    @Override
    public List<Order> findAll(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Order findById(Long id) {
        // TODO Auto-generated method stub
        return orders.get(id);
    }

    @Override
    public void save(Order entity) {
        // TODO Auto-generated method stub
        entity.setId(currentId++);
        orders.put(entity.getId(), entity);
    }

    public List<Order> findAllOrder() {
        return orders.values().stream().toList();
    }
}
