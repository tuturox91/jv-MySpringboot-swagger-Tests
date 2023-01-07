package mate.academy.springboot.swagger.service.mapper.impl;

import mate.academy.springboot.swagger.dto.ProductRequestDto;
import mate.academy.springboot.swagger.model.Product;
import mate.academy.springboot.swagger.service.mapper.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements
        DtoRequestMapper<ProductRequestDto, Product> {

    @Override
    public Product toModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        return product;
    }
}
