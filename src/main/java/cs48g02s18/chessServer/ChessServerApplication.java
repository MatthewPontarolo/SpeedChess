package cs48g02s18.chessServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class ChessServerApplication {
    @RequestMapping("/")
    public String home(){
        return "index yes";
    }

    public static void main(String[] args) {
        SpringApplication.run(ChessServerApplication.class, args);
    }
}
