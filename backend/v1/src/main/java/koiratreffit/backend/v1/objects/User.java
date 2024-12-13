package koiratreffit.backend.v1.objects;


import javax.persistence.Column;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import koiratreffit.backend.v1.customAnnotations.EmailDuplicates;
import koiratreffit.backend.v1.customAnnotations.UsernameDuplicates;

/*
 * 
 * user model
 * 
 * quick note that the constraints seem to not be guranteed to evaluate in the given order.
 * Might be something to fix later to get the correct error message.
 * 
 */

@Document (collection="Users")
public class User {

    @Id
    private String id;

    @NotBlank(message="Username is required")
    @Size(max=10, message="Maximum length for username is 10")
    @UsernameDuplicates
    @Column(name="username")
    private String username;

    @NotBlank(message="Email is required")
    @Pattern(regexp="^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message="Email is in invalid format")
    @EmailDuplicates
    @Column (name="email")
    private String email;

    @NotBlank(message="Password is required")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[\\S]{8,}$", message="Password should include at least one uppercase letter, one lowercase letter and one digit. Password should be over 8 characters long and no whitespaces are allowed.")
    @Column(name="password")
    private String password;

    @Column(name= "imageData")
    private String imageData;

    @Column(name="roles")
    private String roles;

    @Column(name="dogId")
    private String dogId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDogId() {
        return dogId;
    }

    public void setDogId(String dogId) {
        this.dogId = dogId;
    }
}
