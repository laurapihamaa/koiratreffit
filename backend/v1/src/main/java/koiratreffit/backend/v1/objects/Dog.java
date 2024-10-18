package koiratreffit.backend.v1.objects;


import javax.persistence.Column;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection="Dogs")
public class Dog {

    @Id
    private ObjectId id;
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Breed is required")
    private String breed;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotBlank(message = "Age is required")
    private int age;

    //private String location;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    
}
