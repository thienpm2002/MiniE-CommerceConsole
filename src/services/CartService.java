package services;

import java.util.List;

import exceptions.ProductNotFoundException;
import models.CartItem;
import models.Product;
import repositories.CartRepository;

public class CartService {
    private CartRepository cartRepository;
    private ProductService productService;

    public CartService(ProductService productService) {
        this.cartRepository = new CartRepository();
        this.productService = productService;
    }

    public void addToCart(Long productId, int quantity) {
        if (productId == null || productId < 1) {
            throw new IllegalArgumentException("Product ID invalid");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        CartItem existingItem = cartRepository.findCartItemByProductId(productId);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            try {
                Product product = productService.findProductById(productId);
                cartRepository.addCartItem(new CartItem(product, quantity));
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void removeFromCart(Long productId) {
        if (productId == null || productId < 1) {
            throw new IllegalArgumentException("Product ID invalid");
        }

        CartItem existingItem = cartRepository.findCartItemByProductId(productId);
        if (existingItem == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found in cart.");
        }
        cartRepository.removeCartItem(existingItem);
    }

    public void viewCart() {
        List<CartItem> cartItems = cartRepository.getCart();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            cartItems.forEach(System.out::println);
        }
    }

    public void clearCart() {
        List<CartItem> cartItems = cartRepository.getCart();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is already empty.");
        } else {
            cartRepository.clearCart();
        }
    }

    public List<CartItem> getCartItems() {
        return cartRepository.getCart();
    }

}
