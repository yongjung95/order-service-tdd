package com.example.orderservicetdd.product;

import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private final ProductPort productPort;

    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(final AddProductRequest request) {
        final Product product = new Product(request.getName(), request.getPrice(), request.getDiscountPolicy());

        productPort.save(product);
    }

    public GetProductResponse getProduct(final Long productId) {
        final Product product = productPort.getProduct(productId);

        return new GetProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDiscountPolicy());
    }

}