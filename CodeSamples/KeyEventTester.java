import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyEventTester extends JFrame implements KeyListener {

  public static void main(String[] args) {
    JFrame frame = new KeyEventTester();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public KeyEventTester() {
    setSize(500, 300);

    keyPanel = new KeyboardPanel();
    keyPanel.setFocusable(true); // receives keyboard data

    keyPanel.addKeyListener(this);
    Container contentPane = getContentPane();
    contentPane.add(keyPanel, "Center");
  }

  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_DOWN: y += 10; break;
      case KeyEvent.VK_UP: y -= 10; break;
      case KeyEvent.VK_LEFT: x -= 10; break;
      case KeyEvent.VK_RIGHT: x += 10; break;
      default: keyChar = e.getKeyChar();
    }

    keyPanel.repaint();

  }
  
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}


  private class KeyboardPanel extends JPanel {
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(new Color(1.0F, 0.1F, 0.1F));
      g.setFont(new Font("TimesRoman", Font.PLAIN, 48));
      g.drawString(String.valueOf(keyChar), x, y);
    }
  }

  private int x = 100;
  private int y = 100;
  private char keyChar = 'A';
  private JPanel keyPanel;

}
