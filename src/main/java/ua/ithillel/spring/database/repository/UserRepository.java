package ua.ithillel.spring.database.repository;


import org.springframework.data.repository.Repository;
import ua.ithillel.spring.database.entity.User;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findByName(String name);
}
