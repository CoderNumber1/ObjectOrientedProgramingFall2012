
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
        
//        synchronized(GameData.getInstance().field){
//            GameData.getInstance().field.render(graphics);
//        }
        
        GameData data = GameData.getInstance();
        int mode = data.mode;
        if(mode != GameData.MODE_LOAD){
            Iterator<GameFigure> figureIterator = GameData.getInstance().getIterator();
            
            while(figureIterator.hasNext()){
                GameFigure figure = figureIterator.Next();
                
                figure.render(graphics);
            }
        }
        
        if(mode == GameData.MODE_WON){
            graphics.drawImage(GameResources.getInstance().getWonImage(), data.field.getPixelWidth() / 2 - 100, data.field.getPixelHeight() / 2 - 100, null);
        }
        else if(mode == GameData.MODE_LOST){
            graphics.drawImage(GameResources.getInstance().getLostImage(), data.field.getPixelWidth() / 2 - 100, data.field.getPixelHeight() / 2 - 100, null);
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
