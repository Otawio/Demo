package com.sysmap.demo.data;
// Os pacotes DATA e o ENTITIES farão o M(model) do MVC
import com.sysmap.demo.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(UUID userId);
}
