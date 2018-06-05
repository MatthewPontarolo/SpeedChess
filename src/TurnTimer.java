import javax.swing.*;
public class TurnTimer {
    String text;
    int timeCounter = 10;
    Timer timer;
    TurnTimer() {
        //frame.add(timeText);
        //frame.pack();
        //frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
        System.out.println("Timer Starts!");
        System.out.println(timeCounter);
        timer = new Timer(1000, this::update);
        timer.start();
    }
    public void update(java.awt.event.ActionEvent e) {
      if (timeCounter == 0)
      {
        text = "Time's Up!";
        System.out.println(text);
        timer.stop();
      }
      else
      {
        timeCounter--;
        text = "" + timeCounter;
        System.out.println(text);
      }


    }
}
