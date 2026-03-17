package repositories;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void writeToFile() {
        List<Order> orders = findAllOrder();
        try (BufferedWriter br = new BufferedWriter(new FileWriter("data/orders.txt"))) {
            String lineOne = "OrderID | UserID | Total | Status | Date";
            br.write(lineOne);
            br.newLine();
            for (Order o : orders) {
                String line = o.getId() + " " + o.getUser().getId() + " " + o.getTotalAmount() + " " + o.getStatus()
                        + " " + o.getCreatedAt();
                br.write(line);
                br.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
