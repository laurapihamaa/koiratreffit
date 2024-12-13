package koiratreffit.backend.v1.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import koiratreffit.backend.v1.objects.User;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean existsByEmail(String email);
    
}
