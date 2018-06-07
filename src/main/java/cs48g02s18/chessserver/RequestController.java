package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class RequestController {
    private GameServer gameServer;

    @RequestMapping("/")
    public String home(){
        return "index yes";
    }


    public RequestController(GameServer gameServer){
        this.gameServer = gameServer;
    }


    public RequestController(){
        this.gameServer = new GameServer();
    }


    @RequestMapping("/register")
    public String register(@RequestParam(value = "userData") String userDataString) {
        String serverResponse;
        DataPass userData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userData = objectMapper.readValue(userDataString, DataPass.class);
            serverResponse = gameServer.addUser(userData);
        }
        catch (IOException ex) {
            serverResponse = "json processing failure" + ex.toString();
        }

        System.out.print("sent:" + serverResponse + "\n");
        return serverResponse;
    }

    @RequestMapping("/getLobby")
    public String getLobby(){
        return gameServer.getLobby();
    }

    @RequestMapping("/getGameState")
    public DataPassBoardState getGameState(@RequestParam(value = "userData") String userDataString) {
        DataPassBoardState serverResponse;
        DataPass userData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userData = objectMapper.readValue(userDataString, DataPass.class);
            serverResponse = gameServer.getBoardState(userData);
        } catch (IOException ex) {
            serverResponse = null;
        }

        System.out.print("sent:" + serverResponse.toString() + "\n");
        return serverResponse;
    }

    @RequestMapping("/submitMove")
    public String submitMove(@RequestParam(name = "moveData") String moveDataString){
        String serverResponse;
        DataPassMoveData moveData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            moveData = objectMapper.readValue(moveDataString, DataPassMoveData.class);
            System.out.print("move received: " + moveData);
            serverResponse = gameServer.takeRequest(moveData);
        }
        catch (IOException ex) {
            serverResponse = "json processing failure" + ex.toString();
        }

        System.out.print("sent:" + serverResponse + "\n");

        return serverResponse;
    }


    @RequestMapping("/joinGame")
    public String joinGame(@RequestParam(value = "joinData") String joinDataString){
        String serverResponse;
        DataPassJoinGame joinData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            joinData = objectMapper.readValue(joinDataString, DataPassJoinGame.class);
            serverResponse = gameServer.takeRequest(joinData);
        }
        catch (IOException ex) {
            serverResponse = "json processing failure" + ex.toString();
        }

        System.out.print("sent:" + serverResponse + "\n");
        return serverResponse;
    }

    @RequestMapping("/createGame")
    public String createGame(@RequestParam(value = "createData") String createDataString){
        String serverResponse;
        DataPassCreateGame createGameData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createGameData = objectMapper.readValue(createDataString, DataPassCreateGame.class);
            serverResponse = gameServer.takeRequest(createGameData);
        }
        catch (IOException ex) {
            serverResponse = "json processing failure" + ex.toString();
        }

        System.out.print("sent:" + serverResponse + "\n");
        return serverResponse;
    }

    @RequestMapping("/nextGameStep")
    public String nextGameStep(@RequestParam(value = "userData") String userDataString) {
        String serverResponse;
        DataPass userData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userData = objectMapper.readValue(userDataString, DataPass.class);
            serverResponse = gameServer.stepGameForward(userData);
        }
        catch (IOException ex) {
            serverResponse = "json processing failure" + ex.toString();
        }

        System.out.print("sent:" + serverResponse + "\n");
        return serverResponse;
    }
}