package cs48g02s18.chessServer;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    public RequestController(){
    }
    
    @RequestMapping("/submitUsername")
    public String submitUsername(  @RequestParam(value = "username", defaultValue = "") String username,
                            @RequestParam(value = "tempPass", defaultValue = "password") String tempPass){
        this.username = username;
        this.password = tempPass;
        System.out.print(username);
    }

    @RequestMapping("/getLobby")
    public String[] getLobby(){
        return
    }


    @RequestMapping("/submitMove")
    public String submitMove(@RequestParam(name = "moveData") MoveDataPass moveData){
        return new String();
    }

    @RequestMapping("/lockInMove")
    public void lockInMove(){

    }

    @RequestMapping("/joinGame")
    public void joinGame(){

    }
}