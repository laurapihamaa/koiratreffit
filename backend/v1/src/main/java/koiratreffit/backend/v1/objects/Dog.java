package koiratreffit.backend.v1.objects;


import javax.persistence.Column;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection="Dogs")
public class Dog {

    @Id
    private ObjectId id;
    
    @NotBlank(message = "Name is required")
    @Column(name= "name")
    private String name;

    @NotBlank(message = "Breed is required")
    @Column(name= "breed")
    private String breed;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Age is required")
    @Column(name= "age")
    private Double age;

    @Column(name= "imageData")
    private String imageData;

    @NotBlank(message = "Gender is required")
    @Column(name= "gender")
    private String gender;

    @NotBlank(message = "Location is required")
    @Column(name= "location")
    private String location;

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

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    
}
