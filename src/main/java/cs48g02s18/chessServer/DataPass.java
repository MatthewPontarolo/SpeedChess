package cs48g02s18.chessServer;

//chunk of data we will be passing back and forth
//string is an initial stand-in

import java.io.Serializable;

public class DataPass implements Serializable {
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
}