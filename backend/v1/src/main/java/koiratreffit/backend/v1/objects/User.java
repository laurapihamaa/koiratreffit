package koiratreffit.backend.v1.objects;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document (collection="Users")
public class User {

    @Id
    private String id;

    @NotBlank(message="User name is required")
    @Column(name="username")
    private String userName;

    @NotBlank(message="Email is required")
    @Column (name="email")
    private String email;

    @NotBlank(message="Password is required")
    @Column(name="password")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    
}
