package cs48g02s18.chessServer;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    private GameServer gameServer;


    public RequestController(GameServer gameServer){
        this.gameServer = gameServer;
    }


    public RequestController(){
        this.gameServer = new GameServer();
    }


    @RequestMapping("/submitUsername")
    public String submitUsername(@RequestParam(value = "userData") DataPass userData){
        return gameServer.addUser(userData);
    }

    @RequestMapping("/getLobby")
    public String getLobby(){
        return gameServer.getLobby();
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
}