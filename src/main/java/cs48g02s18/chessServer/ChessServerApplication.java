package cs48g02s18.chessServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@SpringBootApplication
public class ChessServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChessServerApplication.class, args);
    }
}
