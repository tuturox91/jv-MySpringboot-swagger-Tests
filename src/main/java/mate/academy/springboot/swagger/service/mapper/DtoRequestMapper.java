package mate.academy.springboot.swagger.service.mapper;

public interface DtoRequestMapper<T, D> {

    D toModel(T requestDto);
}
