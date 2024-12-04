package koiratreffit.backend.v1.objects;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import koiratreffit.backend.v1.customAnnotations.EmailDuplicates;
import koiratreffit.backend.v1.customAnnotations.UsernameDuplicates;

@Document (collection="Users")
public class User {

    @Id
    private String id;

    @NotBlank(message="User name is required")
    @Size(max=10, message="Maximum length for username is 10")
    @Column(name="username")
    @UsernameDuplicates
    private String userName;

    @NotBlank(message="Email is required")
    @Column (name="email")
    @EmailDuplicates
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=8, message="Password should be at least 8 cahracters long")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[\\S]{8,}$", message="password should contain at least one lower case letter, one upper case letter and one digit. Whitespaces are not allowed.")
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
