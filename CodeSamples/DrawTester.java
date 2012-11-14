import gui.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawTester
{  public static void main(String[] args)
   {  DrawTesterFrame frame = new DrawTesterFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}

class DrawTesterFrame extends JFrame
{
  public DrawTesterFrame()
  {
      setSize(300, 300);
      setTitle("Draw Test");

      panel = new DrawPanel(); // Use JPanel for drawing canvas
      Container contentPane = getContentPane();
      contentPane.add(panel, "Center");
   }

   class DrawPanel extends JPanel
   {
      public void paintComponent(Graphics g)
      {  super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
         Rectangle2D.Double r = new Rectangle2D.Double(20, 20, 50, 90);
         g2.fill(r);
         g2.fill(new Ellipse2D.Double(100, 100, 60, 30));
         System.out.println("paintComponet is called");
      }
   }
   private DrawPanel panel;
}
