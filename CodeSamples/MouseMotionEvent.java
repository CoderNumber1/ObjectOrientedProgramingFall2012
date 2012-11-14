// Mouse Motions events: MouseMotionListener
// (1) dragged, (2) moved

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseMotionEvent extends JFrame {

  public static void main(String[] args) {
    MouseMotionEvent me = new MouseMotionEvent();
    me.setTitle("Mouse Motion Events");
    me.setSize(500, 300);
    me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    me.setVisible(true);
  }

  public MouseMotionEvent() {

    canvas = new JPanel();
    canvas.setBackground(Color.blue);
    canvas.addMouseMotionListener(new MouseMotionEventSpy());
    Container cp = getContentPane();
    cp.add(canvas, "Center");
  }

  private class MouseMotionEventSpy implements MouseMotionListener {
    public void mouseMoved(java.awt.event.MouseEvent e) {
      System.out.println("Mouse moved. x = " + e.getX() + " y = " + e.getY());
    }
    public void mouseDragged(java.awt.event.MouseEvent e) {
      System.out.println("Mouse dragged. x = " + e.getX() + " y = " + e.getY());
    }
  }
  private JPanel canvas;
}

