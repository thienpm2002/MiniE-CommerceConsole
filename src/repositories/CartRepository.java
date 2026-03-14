package repositories;

import java.util.ArrayList;
import java.util.List;

import models.CartItem;

public class CartRepository {
    private List<CartItem> cart = new ArrayList<>();

    public void addCartItem(CartItem item) {
        cart.add(item);
    }

    public void removeCartItem(CartItem item) {
        cart.remove(item);
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }

    public CartItem findCartItemByProductId(Long productId) {
        return cart.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }
}
