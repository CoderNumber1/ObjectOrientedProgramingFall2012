
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    public static final int SECTION_WIDTH = 25;
    public static final int SECTION_HEIGHT = 25;
    
    private Animator animator;
    private GameData gameData;
    
    private Graphics graphics;
    private Image dbImage = null;
    
    public GamePanel(Animator animator, GameData gameData){
        this.animator = animator;
        this.gameData = gameData;
        
        super.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
    }
    
    public void startGame(){
        Thread t = new Thread(this.animator);
        t.start();
    }
    
    public void gameRender(){
        if(this.dbImage == null){
            this.dbImage = super.createImage(GamePanel.WIDTH, GamePanel.HEIGHT);
            if (this.dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                this.graphics = this.dbImage.getGraphics();
            }
        }
        
        this.graphics.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        
        synchronized(this.gameData.field){
            this.gameData.field.render(graphics);
        }
//        synchronized(this.gameData.field){
//            for(int x = 0; x < this.gameData.field.field.length; x++){
//                for(int y = 0; y < this.gameData.field.field[x].length; y++){
//                    FieldSection section = this.gameData.field.field[x][y];
//                    
//                    if(section != null){
//                        section.render(graphics);
//                    }
//                }
//            }
//        }
        
//        synchronized(this.gameData.lines){
//            for(Line line : this.gameData.lines){
//                this.graphics.setColor(line.color);
//                this.graphics.drawLine(line.x1, line.y1, line.x2, line.y2);
//            }
//        }
        
        if(this.gameData.mode == GameData.MODE_PLAY){
            synchronized (this.gameData.tank){
                this.gameData.tank.render(this.graphics);
            }

            synchronized (this.gameData.figures){
                GameFigure f;
                for (int i = 0; i < this.gameData.figures.size(); i++) {
                    f = (GameFigure) this.gameData.figures.get(i);
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
