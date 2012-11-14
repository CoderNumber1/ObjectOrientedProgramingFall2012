// Mouse events: MouseListener
// (1) click, (2) entered, (3) exited, (4) pressed, (5) released

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseEvent extends JFrame {

  public static void main(String[] args) {
    MouseEvent me = new MouseEvent();
    me.setTitle("Mouse Events");
    me.setSize(500, 300);
    me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    me.setVisible(true);
  }

  public MouseEvent() {
    canvas = new JPanel();
    canvas.setBackground(Color.black);
    canvas.addMouseListener(new MouseEventSpy());
    Container cp = getContentPane();
    cp.add(canvas, "Center");
  }

  class MouseEventSpy implements MouseListener {
    public void mouseClicked(java.awt.event.MouseEvent e) {
      System.out.println("Mouse clicked. x = " + e.getX() + " y = " + e.getY());
    }
    public void mousePressed(java.awt.event.MouseEvent e) {
      System.out.println("Mouse pressed. x = " + e.getX() + " y = " + e.getY());
    }
    public void mouseReleased(java.awt.event.MouseEvent e) {
      System.out.println("Mouse released. x = " + e.getX() + " y = " + e.getY());
    }
    public void mouseEntered(java.awt.event.MouseEvent e) {
      System.out.println("Mouse entered. x = " + e.getX() + " y = " + e.getY());
    }
    public void mouseExited(java.awt.event.MouseEvent e) {
     System.out.println("Mouse exited. x = " + e.getX() + " y = " + e.getY());
    }
  }

  private JPanel canvas;
}

