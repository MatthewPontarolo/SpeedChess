import javax.swing.*;
public class TurnTimer {
    String text;
    int timeCounter = 10;
    Timer timer;
    int timestamp;
    TurnTimer() {
        //frame.add(timeText);
        //frame.pack();
        //frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
    }
    public void update(java.awt.event.ActionEvent e) {
      if (timeCounter == 0)
      {
        text = "Time's Up!";
        System.out.println(text);
        timer.stop();
        GameHost.endTurn = true;
      }
      else
      {
        timeCounter--;
        text = "" + timeCounter;
        System.out.println(text);
      }
    }
    public void start()
    {
      timestamp = 0;
      System.out.println("Timer Starts!");
      System.out.println(timeCounter);
      timer = new Timer(1000, this::update);
      timer.start();
    }
    public void stop()
    {
      timestamp = timeCounter;
      timer.stop();
      GameHost.endTurn = true;
    }
    public int getTimeStamp()
    {
      return timestamp;
    }
}
