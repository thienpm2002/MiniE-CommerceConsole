package repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Product;

public class ProductRepository implements RepositoryInterface<Long, Product> {
    private Map<Long, Product> products = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    // Find all products with a specific condition (name contains a keyword)
    @Override
    public List<Product> findAll(String condition) {
        return products.values().stream()
                .filter(product -> product.getName().toLowerCase().contains(condition.toLowerCase()))
                .toList();
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        product.setId(currentId++);
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return products.values().stream().toList();
    }
}
