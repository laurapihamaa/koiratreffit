package koiratreffit.backend.v1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import koiratreffit.backend.v1.objects.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
