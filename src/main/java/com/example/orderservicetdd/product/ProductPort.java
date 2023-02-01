package com.example.orderservicetdd.product;

public interface ProductPort {
    void save(final Product product);

    Product getProduct(Long productId);
}
