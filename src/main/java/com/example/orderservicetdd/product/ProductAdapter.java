package com.example.orderservicetdd.product;

public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;

    public ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}