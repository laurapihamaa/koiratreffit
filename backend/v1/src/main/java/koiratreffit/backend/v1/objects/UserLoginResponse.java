package koiratreffit.backend.v1.objects;

public class UserLoginResponse {

    private String username;
    private String email;

    private String imageData;

    private Dog dog;

    public UserLoginResponse(String username, String email, String imageData, Dog dog){
        this.username=username;
        this.email=email;
        this.imageData=imageData;
        this.dog=dog;
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

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    
}
