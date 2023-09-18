package ua.ithillel.model.mapper;

public interface Mapper<E, D> {
    D toDTO(E entity);
}
