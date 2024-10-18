package koiratreffit.backend.v1.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import koiratreffit.backend.v1.objects.Dog;

public interface DogRepository extends MongoRepository<Dog, String>{
    
}
