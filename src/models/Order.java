package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import discount.Discount;
import enums.OrderStatus;

public class Order {
    private Long id;
    private User user;
    private double totalAmount;
    private List<CartItem> items;
    private Date createdAt;
    private OrderStatus status;

    public Order(User user, List<CartItem> items) {
        this.user = user;
        this.items = new ArrayList<>(items);
        this.createdAt = new Date();
        this.status = OrderStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void calculateTotalAmount(Discount discount) {
        double total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        totalAmount = discount.applyDiscount(total);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void pay() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order cannot be paid in its current state.");
        }
        this.status = OrderStatus.PAID;
    }

    public void ship() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("Order cannot be shipped in its current state.");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public void cancel() {
        if (status != OrderStatus.CREATED && status != OrderStatus.PAID) {
            throw new IllegalStateException("Only created or paid orders can be cancelled.");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", user=" + user.toString() + ", totalAmount=" + totalAmount + ", items="
                + items.toString()
                + ", createdAt=" + createdAt + ", status=" + status + "]";
    }

    public void printOrder() {
        System.out.println("--------------------------------------------------");
        System.out.println("Order id: " + id);
        System.out.println("User: " + user.getName());
        System.out.println("Item name | Quantity");
        for (CartItem item : items) {
            System.out.println(item.getProduct().getName() + "      " + item.getQuantity());
        }
        System.out.println("Total: " + totalAmount);
        System.out.println("Order date: " + createdAt);
        System.out.println("Status: " + status);
        System.out.println("--------------------------------------------------");
    }

}
