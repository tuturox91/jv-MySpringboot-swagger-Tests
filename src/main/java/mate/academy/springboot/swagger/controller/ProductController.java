package mate.academy.springboot.swagger.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.swagger.dto.ProductRequestDto;
import mate.academy.springboot.swagger.dto.ProductResponseDto;
import mate.academy.springboot.swagger.model.Product;
import mate.academy.springboot.swagger.service.ProductService;
import mate.academy.springboot.swagger.service.mapper.DtoRequestMapper;
import mate.academy.springboot.swagger.service.mapper.DtoResponseMapper;
import mate.academy.springboot.swagger.utils.SortOrderUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final DtoResponseMapper<ProductResponseDto, Product> responseMapper;
    private final DtoRequestMapper<ProductRequestDto, Product> requestMapper;

    public ProductController(
            ProductService productService,
            DtoResponseMapper<ProductResponseDto, Product> responseMapper,
            DtoRequestMapper<ProductRequestDto, Product> requestMapper) {
        this.productService = productService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping
    @ApiOperation(value = "add new product")
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        return responseMapper.toDto(productService.save(requestMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get product by id")
    public ProductResponseDto getById(@PathVariable Long id) {
        return responseMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete product by id")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/id")
    @ApiOperation(value = "update product by id")
    public ProductResponseDto update(
            @PathVariable Long id,
            @RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.toModel(requestDto);
        product.setId(id);
        return responseMapper.toDto(productService.update(product));
    }

    @GetMapping
    @ApiOperation(value = "find all products")
    public List<ProductResponseDto> findAll(
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "default value is 10") Integer count,
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is 0") Integer page,
            @RequestParam(defaultValue = "id")
            @ApiParam(value = "default value id") String sortBy) {
        List<Sort.Order> orders = SortOrderUtil.parseSort(sortBy);
        Sort sort = Sort.by(orders);
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return productService.findAll(pageRequest).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> findAllByPriceBetween(
            @RequestParam BigDecimal from,
            @RequestParam BigDecimal to,
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "default value is 10") Integer count,
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is 0") Integer page,
            @RequestParam(defaultValue = "id")
            @ApiParam(value = "default value id") String sortBy) {
        List<Sort.Order> orders = SortOrderUtil.parseSort(sortBy);
        Sort sort = Sort.by(orders);
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return productService.findAllByPriceBetween(from, to, pageRequest)
                .stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

}
