
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class GameResources {
    private static GameResources instance;
    
    private Image pathImage;
    private Image wallImage;
    private Image playerTanks[];
    
    private GameResources(){
        String basePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        
        this.pathImage = GameResources.getImage(basePath + separator + "images" + separator + "path.png");
        this.wallImage = GameResources.getImage(basePath + separator + "images" + separator + "wall.png");
        this.playerTanks = new Image[4];
        
        this.playerTanks[GameFigure.DIRECTION_LEFT] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Left.gif");
        this.playerTanks[GameFigure.DIRECTION_RIGHT] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Right.gif");
        this.playerTanks[GameFigure.DIRECTION_FORWARD] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Forward.gif");
        this.playerTanks[GameFigure.DIRECTION_BACKWARD] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Backward.gif");
    }
    
    public Image getPathImage(){
        return this.pathImage;
    }
    
    public Image getWallImage(){
        return this.wallImage;
    }
    
    public Image getPlayerTankImage(int direction){
        return this.playerTanks[direction];
    }
    
    public static GameResources getInstance(){
        if(GameResources.instance == null){
            GameResources.instance = new GameResources();
        }
        
        return GameResources.instance;
    }
    
    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (Exception ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }
}
