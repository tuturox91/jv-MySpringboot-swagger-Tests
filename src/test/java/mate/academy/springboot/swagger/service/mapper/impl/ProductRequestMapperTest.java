package mate.academy.springboot.swagger.service.mapper.impl;

import mate.academy.springboot.swagger.dto.ProductRequestDto;
import mate.academy.springboot.swagger.model.Product;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestMapperTest {

    private ProductRequestMapper requestMapper = new ProductRequestMapper();

    @Test
    public void TestRequestMapper_Ok() {
        ProductRequestDto productDto = new ProductRequestDto();
        productDto.setPrice(BigDecimal.valueOf(1200));
        productDto.setTitle("iPhone 8");
        Product product = requestMapper.toModel(productDto);
        assertEquals(BigDecimal.valueOf(1200), product.getPrice());
        assertEquals("iPhone 8", product.getTitle());
    }
}