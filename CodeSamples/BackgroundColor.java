import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BackgroundColor extends JFrame implements ActionListener {
  public static void main(String[] args) {
    JFrame frame = new BackgroundColor();
    frame.setTitle("Changing Background Colors");
    frame.setSize(500, 300);
    frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
  }

  public BackgroundColor() {

    yellowButton = new JButton("Yellow");
    blueButton = new JButton("Blue");
    redButton = new JButton("Red");

    // background colors of buttons themselves
    yellowButton.setBackground(Color.yellow);
    blueButton.setBackground(Color.blue);
    redButton.setBackground(Color.red);

    yellowButton.addActionListener(this);
    blueButton.addActionListener(this);
    redButton.addActionListener(this);

    buttonPanel = new JPanel();
    buttonPanel.add(yellowButton);
    buttonPanel.add(blueButton);
    buttonPanel.add(redButton);

    Container cp = getContentPane();
    canvas = new JPanel();
    cp.add(canvas, "Center");
    cp.add(buttonPanel, "North");
  }

  public void actionPerformed(ActionEvent e) {
    // When one of buttons are clicked...
    Object source = e.getSource();
    Color color = Color.black;
    if (source == yellowButton) color = Color.yellow;
    else if (source == blueButton) color = Color.blue;
    else if (source == redButton) color = Color.red;
    canvas.setBackground(color);
  }

  JPanel canvas, buttonPanel;
  JButton yellowButton, redButton, blueButton;
}
