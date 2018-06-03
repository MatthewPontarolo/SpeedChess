package cs48g02s18.chessGame;

//import cs48g02s18.chessGame.Board;
import cs48g02s18.chessServer.DataPass;
//import cs48g02s18.chessServer.DataPassMoveData;
import org.springframework.web.client.RestTemplate;


import cs48g02s18.chessServer.Move;

public class ClientConnector {
    String serverURL;
    String username;
    String password;
    RestTemplate restTemplate;

    public ClientConnector(String serverURL) {
        this.serverURL = serverURL;
    }
    public ClientConnector() {
        this.serverURL = "speedchess.herokuapp.com";
    }

/*    public Board getBoard(){

    }*/

    /*public String submitMove(Move move){
        DataPassMoveData data = new DataPassMoveData(username, password, move);
        String returnData;
        returnData = this.restTemplate.getForObject("speedchess.herokuapp.com/submitMove", String.class, data);
        return returnData;
    }*/


    public void setLogin(String login){
        this.username = login;
    }

    public void setPass(String pass){
        this.password = pass;
    }

    public String registerAccount(){
        if (this.username != null && this.password != null) {
            DataPass registerData = new DataPass(username, password);
            String returnData;
            returnData = this.restTemplate.getForObject("speedchess.herokuapp.com/register", String.class, registerData);
            return returnData;
        }
        else {
            return null;
        }
    }

}
