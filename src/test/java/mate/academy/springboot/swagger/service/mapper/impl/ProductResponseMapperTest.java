package mate.academy.springboot.swagger.service.mapper.impl;

import mate.academy.springboot.swagger.dto.ProductResponseDto;
import mate.academy.springboot.swagger.model.Product;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductResponseMapperTest {

    private ProductResponseMapper responseMapper = new ProductResponseMapper();

    @Test
    public void TestResponseMapper_Ok() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(1200));
        product.setTitle("iPhone 8");
        ProductResponseDto productResponse = responseMapper.toDto(product);
        assertEquals(1L, productResponse.getId());
        assertEquals(BigDecimal.valueOf(1200), productResponse.getPrice());
        assertEquals("iPhone 8", productResponse.getTitle());
    }

}