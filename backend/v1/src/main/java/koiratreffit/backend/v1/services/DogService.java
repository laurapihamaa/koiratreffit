package koiratreffit.backend.v1.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import koiratreffit.backend.v1.interfaces.DogServiceInterface;
import koiratreffit.backend.v1.objects.Dog;
import koiratreffit.backend.v1.repositories.DogRepository;

@Service
public class DogService implements DogServiceInterface {

    @Autowired
    private DogRepository dogRepository;

    /*
     * 
     * Get dog by given id
     * 
     * @param id the dog id to fetch
     * @return Dog the dog object based on the id
     * 
     */

    @Override
    public Dog getDogDataById(String id) {
        try {
			Optional<Dog> dogOptional = dogRepository.findById(id);
			return dogOptional.orElse(null);
		}catch(Exception e) {
			throw new RuntimeException("Error getting dog by id");
		}
    }

    /*
     * 
     * fetch dogs and return a random one of them
     * 
     * @return Dog the random dog fetched
     * 
     */

    @Override
    public Dog getRandomDog() {
        try {
			List<Dog> dogs = dogRepository.findAll();
            Random random = new Random();

			return dogs.get(random.nextInt(dogs.size()));

		}catch(Exception e) {
			throw new RuntimeException("Error getting random dog");
		}
    }

    /*
	 * 
	 * Create a new dog to the repository
	 * 
	 * @param dog The dog to store
	 * @return dog the saved dog
	 * 
	 */

	@Override
	public Dog createDog(Dog dog) {
		
		try {
			return dogRepository.save(dog);
		}catch(Exception e) {
			throw new RuntimeException("Error creating dog");
		}
	}
    
}
