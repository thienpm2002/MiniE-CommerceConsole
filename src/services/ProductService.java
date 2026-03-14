package services;

import java.util.List;

import exceptions.ProductNotFoundException;
import models.Product;
import repositories.ProductRepository;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public void addProduct(String name, double price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        if (stock < 1) {
            throw new IllegalArgumentException("Stock cannot be less than 1.");
        }

        productRepository.save(new Product(name, price, stock));
    }

    public void updateStock(Product product, int newStock) {
        product.setStock(newStock);
    }

    public List<Product> searchProducts(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search name cannot be empty.");
        }
        return productRepository.findAll(name);
    }

    public void viewProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(System.out::println);
        }
    }

    public Product findProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        return product;
    }
}
