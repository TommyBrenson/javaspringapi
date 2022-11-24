package ru.tpu.labs.service.impl;

import ru.tpu.labs.model.Product;
import ru.tpu.labs.repository.ProductRepository;
import ru.tpu.labs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return product;
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Product> findProductByPrice(Double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }
}
