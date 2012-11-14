import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class DrawWithMouseClicks extends JFrame implements
        ActionListener, MouseListener
{
  public static void main(String[] args)
   {  DrawWithMouseClicks frame = new DrawWithMouseClicks();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }

  public DrawWithMouseClicks()
  {
      setSize(500, 500);
      setTitle("Draw Shapes with Buttons");

      panel = new DrawPanel(); // Use JPanel for drawing canvas
      panel.addMouseListener(this);
      Container contentPane = getContentPane();
      contentPane.add(panel, "Center");

      widthField = new JTextField("10", 5);
      heightField = new JTextField("10", 5);
      JPanel fieldPanel = new JPanel();
      fieldPanel.add(new JLabel("width:"));
      fieldPanel.add(widthField);
      fieldPanel.add(new JLabel("height:"));
      fieldPanel.add(heightField);
      contentPane.add(fieldPanel, "South");

      rectButton = new JButton("Rectangle");
      ellipseButton = new JButton("Ellipse");
      clearButton = new JButton("clear");
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(rectButton);
      buttonPanel.add(ellipseButton);
      buttonPanel.add(clearButton);
      contentPane.add(buttonPanel, "North");

      rectButton.addActionListener(this);
      ellipseButton.addActionListener(this);
      clearButton.addActionListener(this);

   }

   public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == rectButton) {
        shapeType = RECT_SHAPE;
      } else if (source == ellipseButton) {
        shapeType= CIRCLE_SHAPE;
      } else {
        shapes.clear();
        panel.repaint();
      }
   }

   public void mouseEntered(java.awt.event.MouseEvent e) {}
   public void mouseExited(java.awt.event.MouseEvent e) {}
   public void mouseClicked(java.awt.event.MouseEvent e) {}
   public void mouseReleased(java.awt.event.MouseEvent e) {}
   public void mousePressed(java.awt.event.MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      double w = Double.parseDouble(widthField.getText());
      double h = Double.parseDouble(heightField.getText());

      if (shapeType == RECT_SHAPE)
        shapes.add(new Rectangle2D.Double(x,y,w,h));
      else
        shapes.add(new Ellipse2D.Double(x,y,w,h));

      panel.repaint();
   }

   class DrawPanel extends JPanel
   {
      public void paintComponent(Graphics g)
      {  super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
         for (Shape s : shapes)
           g2.draw(s);
      }
   }

   private DrawPanel panel;
   private JTextField widthField;
   private JTextField heightField;
   private JButton rectButton;
   private JButton ellipseButton;
   private JButton clearButton;
   private RectangularShape shape;
   private ArrayList<Shape> shapes = new ArrayList<Shape>();
   private static final int RECT_SHAPE = 0;
   private static final int CIRCLE_SHAPE = 1;
   private int shapeType = RECT_SHAPE;

}
