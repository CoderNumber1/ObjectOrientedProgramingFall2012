import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
This program shows a clock that is updated once per second.
 */
public class TimerTester extends JFrame {

  public static void main(String[] args) {
    TimerTester frame = new TimerTester();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  public TimerTester() {

    final int FIELD_WIDTH = 20;
    textField = new JTextField(FIELD_WIDTH);

    add(textField);

    TimerListener listener = new TimerListener();

    final int DELAY = 1000;
    // Milliseconds between timer ticks
    Timer t = new Timer(DELAY, listener);
    t.start();

  }

  private class TimerListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      Date now = new Date();
      textField.setText(now.toString());
    }

  }

  private JTextField textField;
}
