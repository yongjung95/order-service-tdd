package com.example.orderservicetdd.product;

import com.example.orderservicetdd.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.*;

public class ProductApiTest extends ApiTest {

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        // API 요청
        final ExtractableResponse<Response> response = RestAssured.given().log().all() // 요청을 보내는 로그를 남기겠다는 뜻.
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회() {
        // 상품등록
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        // API 요청
        final ExtractableResponse<Response> response = RestAssured.given().log().all() // 요청을 보내는 로그를 남기겠다는 뜻.
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();

        // 상품조회
        Long productId = 1L;

        final ExtractableResponse<Response> response1 = RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract();

        assertThat(response1.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response1.jsonPath().getString("name")).isEqualTo("상품명");
    }
}
