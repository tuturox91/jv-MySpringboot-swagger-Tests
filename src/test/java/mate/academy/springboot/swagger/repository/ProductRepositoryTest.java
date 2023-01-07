package mate.academy.springboot.swagger.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mate.academy.springboot.swagger.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest  {
    @Container
    static MySQLContainer<?> database = new MySQLContainer<>("mysql:8")
            .withDatabaseName("springboot")
            .withPassword("springboot")
            .withUsername("springboot");

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
    }

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Sql("/scripts/init_four_products.sql")
    void shouldReturnProductPriceGreaterThan1200_DESC() {
        List<Sort.Order> order = new ArrayList<>();
        order.add(new Sort.Order(Sort.Direction.DESC, "price"));
        Sort sort = Sort.by(order);
        Pageable pageable  = PageRequest.of(0, 10, sort);
        List<Product> acutal = productRepository.findAllByPriceBetween(BigDecimal.valueOf(1200),
                BigDecimal.valueOf(Integer.MAX_VALUE), pageable);
        Assertions.assertEquals(1, acutal.size());
        Assertions.assertEquals("iPhone XI", acutal.get(0).getTitle());
    }

    @Test
    @Sql("/scripts/init_four_products.sql")
    void shouldReturnProductPriceBetweenFrom700to800_ASC() {
        List<Sort.Order> order = new ArrayList<>();
        order.add(new Sort.Order(Sort.Direction.ASC, "price"));
        Sort sort = Sort.by(order);
        Pageable pageable  = PageRequest.of(0, 10, sort);
        List<Product> acutal = productRepository.findAllByPriceBetween(BigDecimal.valueOf(700),
                BigDecimal.valueOf(800), pageable);
        Assertions.assertEquals(2, acutal.size());
        Assertions.assertEquals("iPhone 8", acutal.get(0).getTitle());
        Assertions.assertEquals(BigDecimal.valueOf(700), acutal.get(0).getPrice());
        Assertions.assertEquals("iPhone 9", acutal.get(1).getTitle());
        Assertions.assertEquals(BigDecimal.valueOf(800), acutal.get(1).getPrice());
    }

    @Test
    @Sql("/scripts/init_four_products.sql")
    void shouldReturnProductPriceBetweenFrom700to1000SortByTitle_DESC() {
        List<Sort.Order> order = new ArrayList<>();
        order.add(new Sort.Order(Sort.Direction.ASC, "title"));
        Sort sort = Sort.by(order);
        Pageable pageable  = PageRequest.of(0, 10, sort);
        List<Product> acutal = productRepository.findAllByPriceBetween(BigDecimal.valueOf(700),
                BigDecimal.valueOf(1000), pageable);
        Assertions.assertEquals(3, acutal.size());
        Assertions.assertEquals("A7", acutal.get(0).getTitle());
        Assertions.assertEquals(BigDecimal.valueOf(1000), acutal.get(0).getPrice());
        Assertions.assertEquals("iPhone 8", acutal.get(1).getTitle());
        Assertions.assertEquals(BigDecimal.valueOf(700), acutal.get(1).getPrice());
    }
}