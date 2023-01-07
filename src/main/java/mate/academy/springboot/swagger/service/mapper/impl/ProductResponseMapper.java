package mate.academy.springboot.swagger.service.mapper.impl;

import mate.academy.springboot.swagger.dto.ProductResponseDto;
import mate.academy.springboot.swagger.model.Product;
import mate.academy.springboot.swagger.service.mapper.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements DtoResponseMapper<ProductResponseDto, Product> {

    @Override
    public ProductResponseDto toDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setPrice(model.getPrice());
        responseDto.setTitle(model.getTitle());
        return responseDto;
    }
}
