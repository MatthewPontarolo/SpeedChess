package cs48g02s18.chessgame;

//import cs48g02s18.chessGame.Board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs48g02s18.chessserver.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

//import cs48g02s18.chessServer.DataPassMoveData;

public class ClientConnector {
    /*
    String serverURL;
    String username;
    String password;
    RestTemplate restTemplate;
    StringBuilder serverResponses;
    ObjectMapper jsonMapper;
    private DataPassBoardState lastBoard;

    public ClientConnector(String serverURL) {
        this.serverURL = serverURL;
        setupClientConnector();
    }
    private void setupClientConnector(){
        this.restTemplate = new RestTemplate();
        serverResponses = new StringBuilder();
        setUpJsonMapper();
    }
    private void setUpJsonMapper(){
        this.jsonMapper = new ObjectMapper();
    }

    StringBuilder getServerResponses(){
        return serverResponses;
    }
    public ClientConnector() {
        this.serverURL = "https://speedchess.herokuapp.com";
        setupClientConnector();
    }

    public MoveData getOpponentsLastMove(){
        return lastBoard.getOpponentLastMove();
    }

    public Board getNewBoard(){
        return new Board(lastBoard.getBoardData());
    }

    private void communicate(String urlPostfix, String argName, DataPass data){
        String url = this.serverURL + urlPostfix;
        serverResponses.append("call to:" + urlPostfix);

        String jsonString = new String();
        try {
            jsonString = jsonMapper.writeValueAsString(data);
            System.out.print(jsonString + " --- jsonstring \n");
        }
        catch (JsonProcessingException e) {
            System.out.print(e.toString() + "--- could not map to JSON ---");
        }

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        componentsBuilder.queryParam(argName, jsonString);

        System.out.print(componentsBuilder.build().toUri() + " --- uri + \n");

        String responseString = restTemplate.getForObject(componentsBuilder.build().toUri(), String.class);
        serverResponses.append("- reply: :" + responseString + "\n");

    }





    public void submitMove(MoveData move){
        DataPassMoveData data = new DataPassMoveData(username, password, move);
        this.communicate("/submitMove", "moveData", data);
    }



    public void updateBoardFromServer(){ //returns 1 if it updated something
        DataPass auth = new DataPass(username, password);
        String urlPostFix = "/getGameState";
        String url = this.serverURL + urlPostFix;
        serverResponses.append("call to:" + urlPostFix);

        String jsonString = new String();
        try {
            jsonString = jsonMapper.writeValueAsString(auth);
            System.out.print(jsonString + " --- jsonstring \n");
        }
        catch (JsonProcessingException e) {
            System.out.print(e.toString() + "--- could not map to JSON ---");
        }

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        String argName = "userData";
        componentsBuilder.queryParam(argName, jsonString);

        System.out.print(componentsBuilder.build().toUri() + " --- uri + \n");

        this.lastBoard = restTemplate.getForObject(componentsBuilder.build().toUri(), DataPassBoardState.class);

        serverResponses.append("- reply: :" + this.lastBoard.toString() + "\n");
    }

    public void createGame(String gameName){
        DataPassCreateGame data = new DataPassCreateGame(username, password, gameName);
        communicate("/createGame", "createData", data);
    }

    public void joinGame(String gameName){
        DataPassJoinGame data = new DataPassJoinGame(username, password, gameName);
        communicate("/joinGame", "createData", data);
    }


    public void setLogin(String login){
        this.username = login;
    }

    public void setPass(String pass){
        this.password = pass;
    }

    public void main(String[] args) {
        SpringApplication.run(ClientConnector.class, args);
    }

    public void registerAccount(){
        if (this.username != null && this.password != null) {
            DataPass registerData = new DataPass(username, password);
            communicate("/register", "userData", registerData);

            /*;
            String returnData;
            String url = "https://speedchess.herokuapp.com/register";
            UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
            HttpEntity<DataPass> httpData = new HttpEntity<DataPass>(registerData);
            componentsBuilder.queryParam("userData", httpData);

            return returnData = this.restTemplate.getForObject(componentsBuilder.build().toUri(), String.class);*/

            //HttpEntity<DataPass> httpData = new HttpEntity<DataPass>(registerData);
            //returnData = this.restTemplate.postForObject(componentsBuilder.build().toUri(), registerData, String.class);
      //  }
    //}
}
