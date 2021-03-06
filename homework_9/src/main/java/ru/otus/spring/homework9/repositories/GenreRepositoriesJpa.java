package ru.otus.spring.homework9.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework9.models.Genre;

import java.util.Optional;

@Repository
public interface GenreRepositoriesJpa extends CrudRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
