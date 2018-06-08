package cs48g02s18.chessgame;

//import cs48g02s18.chessGame.Board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs48g02s18.chessserver.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;

//import cs48g02s18.chessServer.DataPassMoveData;

@SpringBootApplication
public class ClientConnector {
    private String serverURL;
    private String username;
    private String password;
    private RestTemplate restTemplate;
    private StringBuilder serverResponses;
    private ObjectMapper jsonMapper;
    private DataPassBoardState lastBoard;
    private GameHost gameHost;

    public ClientConnector(String serverURL) {
        this.serverURL = serverURL;
        setupClientConnector();
    }
    private void setupClientConnector() {
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

    public void setGameHost(GameHost gameHost) {
        this.gameHost = gameHost;
    }

    public int getPlayerNumber() {
        return lastBoard.getPlayerNumber();
    }

    public void setUpAndConnect(){
        String username = (String)JOptionPane.showInputDialog("username");
        //String password = (String)JOptionPane.showInputDialog("password");
        String password = username;
        this.setLogin(username);
        this.setPass(password);
        this.registerAccount();

        int choice;
        Object[] options = {"Create New Game", "Game Lobby", "Join Game"};
        do {
            choice = JOptionPane.showOptionDialog(new JFrame(), "Choose one:", "SpeedChess",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            if (choice == 1){
                String games;
                games = restTemplate.getForObject(this.serverURL + "/getLobby", String.class);
                JOptionPane.showMessageDialog(new JFrame(), games);
            }
        } while (choice == 1);

        String gameName = (String)JOptionPane.showInputDialog("gameName");
        if (choice == 0) { //new game
            this.createGame(gameName);
        }
        else if (choice == 2) {
            this.joinGame(gameName);
        }
    }

    public void lockInMove(){
        DataPass data = new DataPass(username, password);
        this.communicate("/nextGameStep", "userData", data);
    }

    public Move getOpponentsLastMove(){
        return lastBoard.getOpponentLastMove();
    }

    public Board getNewBoard(){
        if (lastBoard == null) return new Board(new Player(1), new Player(0));
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





    public void submitMove(Move move){
        DataPassMoveData data = new DataPassMoveData(username, password, move);
        this.communicate("/submitMove", "moveData", data);
    }


    public boolean updateBoardFromServer() {
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
            JOptionPane.showMessageDialog(new JFrame(), "JSON processing error!");
        }

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        String argName = "userData";
        componentsBuilder.queryParam(argName, jsonString);

        System.out.print(componentsBuilder.build().toUri() + " --- uri + \n");

        this.lastBoard = restTemplate.getForObject(componentsBuilder.build().toUri(), DataPassBoardState.class);

        serverResponses.append("- reply: :" + this.lastBoard.toString() + "\n");

        /*if (Main.scene != null) {
			System.out.println("it made it in here at least");
        	Main.scene.redrawBoard();
		}*/

        //return (this.lastBoard.getBoardData() == this.gameHost. ) //todo finish this to return whether or not there is a new board that needs to be updated into Ghost
        return false;
    }

    public void createGame(String gameName){
        DataPassCreateGame data = new DataPassCreateGame(username, password, gameName);
        communicate("/createGame", "createData", data);
    }

    public void joinGame(String gameName){
        DataPassJoinGame data = new DataPassJoinGame(username, password, gameName);
        communicate("/joinGame", "joinData", data);
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
        }
    }

}
