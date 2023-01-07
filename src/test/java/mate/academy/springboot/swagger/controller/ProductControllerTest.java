package mate.academy.springboot.swagger.controller;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import mate.academy.springboot.swagger.model.Product;
import mate.academy.springboot.swagger.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldShowAllProducts() {
        List<Product> mockProducts = List.of(
                new Product(1L, "iPhone 8", BigDecimal.valueOf(800)),
                new Product(2L, "iPhone 9", BigDecimal.valueOf(900)),
                new Product(3L, "iPhone X", BigDecimal.valueOf(1200))
        );
        List<Sort.Order> order = new ArrayList<>();
        order.add(new Sort.Order(Sort.Direction.DESC, "price"));
        Sort sort = Sort.by(order);
        PageRequest pageRequest  = PageRequest.of(0, 10, sort);
        Mockito.when(productService.findAll(pageRequest)).thenReturn(mockProducts);

        RestAssuredMockMvc
                .given()
                .queryParam("sortBy", "price:DESC")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].title", Matchers.equalTo("iPhone 8"))
                .body("[0].price", Matchers.equalTo(800))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].title", Matchers.equalTo("iPhone 9"))
                .body("[1].price", Matchers.equalTo(900))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].title", Matchers.equalTo("iPhone X"))
                .body("[2].price", Matchers.equalTo(1200));
    }

    @Test
    void shouldReturnAllProductsWithPriceBetweenTwoValues() {
        BigDecimal from = BigDecimal.valueOf(700);
        BigDecimal to = BigDecimal.valueOf(1000);
        List<Sort.Order> order = new ArrayList<>();
        order.add(new Sort.Order(Sort.Direction.DESC, "price"));
        Sort sort = Sort.by(order);
        PageRequest pageRequest  = PageRequest.of(0, 10, sort);
        List<Product> mockProducts = List.of(new Product(42L, "iPhone 11", BigDecimal.valueOf(800)));
        Mockito.when(productService.findAllByPriceBetween(from, to, pageRequest)).thenReturn(mockProducts);

        RestAssuredMockMvc
                .given()
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("sortBy", "price:DESC")
                .when()
                .get("/products/by-price")
                .then()
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(42))
                .body("[0].title", Matchers.equalTo("iPhone 11"))
                .body("[0].price", Matchers.equalTo(800));
    }
}