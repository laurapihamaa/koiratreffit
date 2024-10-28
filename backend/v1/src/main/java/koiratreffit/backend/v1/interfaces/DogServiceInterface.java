package koiratreffit.backend.v1.interfaces;

import org.springframework.stereotype.Service;

import koiratreffit.backend.v1.objects.Dog;

@Service
public interface DogServiceInterface {

    Dog getDogDataById(String id);

    Dog getRandomDog();
    
}
