package koiratreffit.backend.v1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import koiratreffit.backend.v1.interfaces.DogServiceInterface;
import koiratreffit.backend.v1.objects.Dog;
import koiratreffit.backend.v1.repositories.DogRepository;

@Service
public class DogService implements DogServiceInterface {

    @Autowired
    private DogRepository dogRepository;

    @Override
    public Dog getDogDataById(String id) {
        try {
			Optional<Dog> dogOptional = dogRepository.findById(id);
			return dogOptional.orElse(null);
		}catch(Exception e) {
			throw new RuntimeException("Error getting dog by id");
		}
    }
    
}
