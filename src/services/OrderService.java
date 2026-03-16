package services;

import java.util.List;

import models.CartItem;
import models.Order;
import models.Product;
import models.User;
import payment.Payment;
import repositories.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void checkOut(User user, List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cart cannot be empty when creating an order.");
        }
        Order order = new Order(user, items);
        order.calculateTotalAmount();
        orderRepository.save(order);
    }

    public void viewOrders() {
        List<Order> orders = orderRepository.findAllOrder();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            orders.forEach(order -> order.printOrder());
        }
    }

    public void payOrder(Long orderId, Payment paymentMethod) {
        // check order exist
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
        }
        // Check stock
        List<CartItem> items = order.getItems();
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for product: " + product.getName());
            }
        }
        // Update money user
        User user = order.getUser();
        double totalAmount = order.getTotalAmount(); // Calculate total amount width discount
        paymentMethod.processPayment(user, totalAmount);
        // Update stock
        for (CartItem item : items) {
            Product product = item.getProduct();
            int newStock = product.getStock() - item.getQuantity();
            product.setStock(newStock);
        }
        // Set status order
        order.pay();
    }

    public void payCancel(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
        }
        User user = order.getUser();
        double totalAmount = order.getTotalAmount();

        user.deposit(totalAmount);

        order.cancel();

    }

    public void shipOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found.");
        }
        order.ship();
    }

}
