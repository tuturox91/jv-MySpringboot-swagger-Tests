package mate.academy.springboot.swagger.service.mapper;

public interface DtoResponseMapper<T, D> {

    T toDto(D model);
}
