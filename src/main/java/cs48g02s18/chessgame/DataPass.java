package cs48g02s18.chessgame;

//chunk of data we will be passing back and forth
//string is an initial stand-in

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPass {
    private String username;
    private String password;

    public DataPass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "DataPass{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}