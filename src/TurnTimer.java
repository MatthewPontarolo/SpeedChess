import javax.swing.*;
import javafx.application.Platform;

public class TurnTimer {
    public static final int TIME_LIMIT = 5;
    String text;
    int timeCounter = TIME_LIMIT;
    Timer timer;
    int timestamp;

    public TurnTimer() {

    }

    public void update(java.awt.event.ActionEvent e) {
      if (timeCounter == 0) {
        text = "Time's Up!";
        System.out.println(text);
        GameHost.forfeit = true;
        stop();
      } else {
        timeCounter--;
        text = "" + timeCounter;
        System.out.println(text);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
        if (GameHost.gameEnded == false)
        {
          SpeedChess.updateTimeView(timeCounter);  
        }
			}
		});
      }
    }

    public void start() {
      timestamp = 0;
      timeCounter = TIME_LIMIT;
      System.out.println("Timer Starts!");
      System.out.println(timeCounter);
      timer = new Timer(1000, this::update);
      timer.start();
    }
    public void stop() {
      timer.stop();
      timestamp = timeCounter;
      System.out.println("stop");
      if (timestamp == 0) {
        GameHost.forfeit();
        System.out.println("forfeit");
      }
    }

    public int getTimeStamp()
    {
      return timestamp;
    }
}
