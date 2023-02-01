package com.example.orderservicetdd.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        productService.addProduct(request);
    }

    @Test
    void 상품조회() {
        // 상품등록
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        productService.addProduct(request);
        final long productId = 1L;

        // 상품을 조회
        final GetProductResponse response = productService.getProduct(productId);

        // 상품의 응답을 검증
        assertThat(response).isNotNull();
    }
}
