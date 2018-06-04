import javax.swing.*;
public class TurnTimer {
    JFrame frame = new JFrame("Timer");
    JLabel timeText = new JLabel("Timer Starts!");
    int timeCounter = 10;
    TurnTimer() {
        frame.add(timeText);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Timer(1000, this::update).start();
    }
    void update(java.awt.event.ActionEvent e) {
      if (timeCounter == 0)
      {
        timeText.setText("Time's Up!");
      }
      else
      {
        timeText.setText("" + (--timeCounter));
      }
    }
}
