import gui.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawMoveTester
{  public static void main(String[] args)
   {  DrawMoveFrame frame = new DrawMoveFrame();
      frame.setTitle("DrawMoveTester");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}

class DrawMoveFrame extends JFrame
{  public DrawMoveFrame()
   {  final int DEFAULT_FRAME_WIDTH = 500;
      final int DEFAULT_FRAME_HEIGHT = 300;
      setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

      // construct components

      panel = new RectanglePanel();
      JPanel buttonPanel = new JPanel();

      ActionListener listener = new DirectionListener();

      upButton = new JButton("Up");
      upButton.addActionListener(listener);

      downButton = new JButton("Down");
      downButton.addActionListener(listener);

      leftButton = new JButton("Left");
      leftButton.addActionListener(listener);

      rightButton = new JButton("Right");
      rightButton.addActionListener(listener);

      // add components to content pane

      Container contentPane = getContentPane();
      contentPane.add(panel, "Center");

      buttonPanel.add(upButton);
      buttonPanel.add(downButton);
      buttonPanel.add(leftButton);
      buttonPanel.add(rightButton);

      contentPane.add(buttonPanel, "South");
   }

   private RectanglePanel panel;
   private JButton upButton;
   private JButton downButton;
   private JButton leftButton;
   private JButton rightButton;

   // inner class definition

   private class DirectionListener implements ActionListener
   {  public void actionPerformed(ActionEvent event)
      {  // find the button that was clicked

         Object source = event.getSource();

         if (source == upButton)
            panel.moveRectangle(0, -1);
         else if (source == downButton)
            panel.moveRectangle(0, 1);
         else if (source == leftButton)
            panel.moveRectangle(-1, 0);
         else if (source == rightButton)
            panel.moveRectangle(1, 0);
      }
   }
}

class RectanglePanel extends JPanel
{  public RectanglePanel()
   {  rect = new Rectangle2D.Double(0, 0, RECT_WIDTH, RECT_HEIGHT);
   }

   public void paintComponent(Graphics g)
   {  super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.draw(rect);
   }

   /**
      Moves the rectangle and repaints it. The rectangle
      is moved by multiples of its full width or height.
      @param dx the number of width units
      @param dy the number of height units
   */
   public void moveRectangle(int dx, int dy)
   {  rect.setRect(
              rect.getX() + dx * RECT_WIDTH,
              rect.getY() + dy * RECT_HEIGHT,
              RECT_WIDTH, RECT_HEIGHT);
      repaint();
   }

   private Rectangle2D.Double rect;
   private static final int RECT_WIDTH = 20;
   private static final int RECT_HEIGHT = 30;
}
