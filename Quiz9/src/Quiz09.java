
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Quiz09 extends JFrame {
    public static void main(String[] args){
        Quiz09 quiz = new Quiz09();
        
        quiz.setTitle("Quiz 09");
        quiz.setSize(600, 600);
        quiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quiz.setVisible(true);
    }
    
    public Quiz09(){
        this.xCorner = 250;
        this.yCorner = 250;
        
        this.xDelta = -1.0;
        this.yDelta = -1.0;
        
        this.canvas = new CircleCanvas();
        this.canvas.addMouseListener(new ClickListener());
        this.canvas.addMouseMotionListener(new DragListener());
        
        Container cp = super.getContentPane();
        cp.add(this.canvas, "Center");
    }
    
    public class CircleCanvas extends JPanel{
        public CircleCanvas(){
            this.circle = new Ellipse2D.Double(xCorner, yCorner, 100, 100);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D)g;
            
            g2.draw(circle);
        }
        
        Ellipse2D.Double circle;
    }
    
    public class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            int x = e.getX();
            int y = e.getY();
            
            if(containsPointer(x, y)){
                xDelta = x - canvas.circle.getX();
                yDelta = y - canvas.circle.getY();
            }
            else{
                yDelta = -1.0;
                xDelta = -1.0;
            }
        }
    }
    
    public class DragListener extends MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent e){
            if(yDelta >= 0.0 || xDelta >= 0.0){
                xCorner = e.getX() - xDelta;
                yCorner = e.getY() - yDelta;
                
                canvas.circle.setFrame(xCorner, yCorner, 100, 100);
                repaint();
            }
        }
    }
    
    public boolean containsPointer(double xPointer, double yPointer){
        double xCenter = canvas.circle.getWidth() / 2.0 + canvas.circle.getX();
        double yCenter = canvas.circle.getHeight() / 2.0 + canvas.circle.getY();

        double distance = Math.sqrt(Math.pow(xCenter - xPointer, 2) + Math.pow(yCenter - yPointer, 2));

        return (distance <= canvas.circle.getWidth() / 2.0);
    }
    
    private CircleCanvas canvas;
    
    private double xDelta;
    private double yDelta;
    
    private double xCorner;
    private double yCorner;
}
