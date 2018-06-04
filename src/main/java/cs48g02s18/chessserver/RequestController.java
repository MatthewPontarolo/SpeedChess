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
    public String register(@RequestParam(value = "userData") DataPass userData) {
        String serverResponse;
        //DataPass userData;
        //ObjectMapper objectMapper = new ObjectMapper();
        //try {
          //  userData = objectMapper.readValue(userDataString, DataPass.class);
            serverResponse = gameServer.addUser(userData);
        //}
        //catch (IOException ex) {
          //  serverResponse = "json processing failure" + ex.toString();
        //}

        System.out.print("sent:" + serverResponse + "\n");
        return serverResponse;
    }

    @RequestMapping("/getLobby")
    public String getLobby(){
        return gameServer.getLobby();
    }

    @RequestMapping("/getGameState")
    public DataPassBoardState getGameState(@RequestParam(value = "userData") HttpEntity<DataPass> userData){
        return gameServer.getBoardState(userData.getBody());
    }

    @RequestMapping("/submitMove")
    public String submitMove(@RequestParam(name = "moveData") DataPassMoveData moveData){
        return gameServer.takeRequest(moveData);
    }
/*
    @RequestMapping("/lockInMove")
    public void lockInMove(){

    }
*/
    @RequestMapping("/joinGame")
    public String joinGame(@RequestParam(value = "joinData") DataPassJoinGame joinData){
        return gameServer.takeRequest(joinData);
    }

    @RequestMapping("/createGame")
    public String createGame(@RequestParam(value = "createData") DataPassCreateGame createData){
        return gameServer.takeRequest(createData);
    }

    @RequestMapping("/nextGameStep")
    public String nextGameStep(@RequestParam(value = "userData") DataPass userData) {
        return gameServer.stepGameForward(userData);
    }
}