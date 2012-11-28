
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
//    public static final int WIDTH = 600;
//    public static final int HEIGHT = 600;
//    
//    public static final int SECTION_WIDTH = 25;
//    public static final int SECTION_HEIGHT = 25;
    
    private Animator animator;
    
    private Graphics graphics;
    private Image dbImage = null;
    
    public GamePanel(Animator animator){
        this.animator = animator;
        
        super.setPreferredSize(new Dimension(600, 600));
    }
    
    public void startGame(){
        Thread t = new Thread(this.animator);
        t.start();
    }
    
    public void gameRender(){
        if(this.dbImage == null){
            this.dbImage = super.createImage(600, 600);
            if (this.dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                this.graphics = this.dbImage.getGraphics();
            }
        }
        
        this.graphics.clearRect(0, 0, 600, 600);
        
        synchronized(GameData.getInstance().field){
            GameData.getInstance().field.render(graphics);
        }
        
        if(GameData.getInstance().mode == GameData.MODE_PLAY){
            synchronized (GameData.getInstance().tank){
                GameData.getInstance().tank.render(this.graphics);
            }

            synchronized (GameData.getInstance().figures){
                GameFigure f;
                for (int i = 0; i < GameData.getInstance().figures.size(); i++) {
                    f = (GameFigure) GameData.getInstance().figures.get(i);
                    f.render(this.graphics);
                }    
            }
        }
    }
    
    public void printScreen() {
        Graphics g;
        try {
            g = super.getGraphics();
            if ((g != null) && (this.dbImage != null)) {
                g.drawImage(this.dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
}
