import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawWithButtons extends JFrame implements ActionListener
{
  public static void main(String[] args)
   {  DrawWithButtons frame = new DrawWithButtons();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }

  public DrawWithButtons()
  {
      setSize(500, 500);
      setTitle("Draw Shapes with Buttons");

      panel = new DrawPanel(); // Use JPanel for drawing canvas
      Container contentPane = getContentPane();
      contentPane.add(panel, "Center");

      xField = new JTextField("100", 5);
      yField = new JTextField("100", 5);
      widthField = new JTextField("30", 5);
      heightField = new JTextField("30", 5);
      JPanel fieldPanel = new JPanel();
      fieldPanel.add(new JLabel("x:"));
      fieldPanel.add(xField);
      fieldPanel.add(new JLabel("y:"));
      fieldPanel.add(yField);
      fieldPanel.add(new JLabel("width:"));
      fieldPanel.add(widthField);
      fieldPanel.add(new JLabel("height:"));
      fieldPanel.add(heightField);
      contentPane.add(fieldPanel, "South");

      rectButton = new JButton("Rectangle");
      ellipseButton = new JButton("Ellipse");
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(rectButton);
      buttonPanel.add(ellipseButton);
      contentPane.add(buttonPanel, "North");

      rectButton.addActionListener(this);
      ellipseButton.addActionListener(this);

   }

   public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      double x = Double.parseDouble(xField.getText());
      double y = Double.parseDouble(yField.getText());
      double w = Double.parseDouble(widthField.getText());
      double h = Double.parseDouble(heightField.getText());

      if (source == rectButton) {
        shape = new Rectangle2D.Double(x,y,w,h);
      } else if (source == ellipseButton) {
        shape = new Ellipse2D.Double(x,y,w,h);
      }
      panel.repaint();
   }

   class DrawPanel extends JPanel
   {
      public void paintComponent(Graphics g)
      {  super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
	 if (shape != null)
	   g2.draw(shape);
      }
   }
   private DrawPanel panel;
   private JTextField xField;
   private JTextField yField;
   private JTextField widthField;
   private JTextField heightField;
   private JButton rectButton;
   private JButton ellipseButton;
   RectangularShape shape = null;
}
