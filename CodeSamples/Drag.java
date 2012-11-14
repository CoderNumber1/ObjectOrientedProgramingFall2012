import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Drag extends JFrame {
  public static void main(String[] args) {
    Drag d = new Drag();
    d.setTitle("Drag something with mouse");
    d.setSize(700, 500);
    d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    d.setVisible(true);
  }

  public Drag() {

    canvas = new Canvas();
    canvas.addMouseListener(new ClickListener());
    canvas.addMouseMotionListener(new DragListener());

    Container cp = getContentPane();
    cp.add(canvas, "Center");
  }

  private class ClickListener extends MouseAdapter {
    public void mousePressed(java.awt.event.MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      System.out.println("click "+x+" "+y);

      canvas.active = -1;
      for (int i = 0; i < canvas.boxes.size(); i++) {
        Rectangle2D.Double r = canvas.boxes.get(i);
        if (r.contains(x, y)) {
          canvas.active = i;
          System.out.println("active = "+canvas.active);
          return;
        }
      }
    }
  }

  private class DragListener extends MouseMotionAdapter {
    public void mouseDragged(java.awt.event.MouseEvent e) {
      if (canvas.active < 0)
         return;

      Rectangle2D.Double r = canvas.boxes.get(canvas.active);
      int x = e.getX();
      int y = e.getY();
      r.setRect(x, y, RWIDTH, RHEIGHT);
      repaint();
    }
  }

  private class Canvas extends JPanel {
    Canvas() {
      boxes = new ArrayList<Rectangle2D.Double>();
      boxes.add(new Rectangle2D.Double(10, 10, RWIDTH, RHEIGHT));
      boxes.add(new Rectangle2D.Double(20, 20, RWIDTH, RHEIGHT));
      boxes.add(new Rectangle2D.Double(30, 50, RWIDTH, RHEIGHT));
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (int i = 0; i < boxes.size(); i++) {
        Rectangle2D.Double r = boxes.get(i);
        g2.draw(r);
      }
    }

    ArrayList<Rectangle2D.Double> boxes;
    int active = -1;
  }

  Canvas canvas;
  final int RWIDTH = 10;
  final int RHEIGHT = 10;
}
