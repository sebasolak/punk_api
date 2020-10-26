package punkapi.demo.model.dto;

public class UserDto {

    private String login;
    private String email;
    private String password;

    public UserDto(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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