
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class GameResources implements FieldObserver {
    private static GameResources instance;
    
    private Image wonImage;
    private Image lostImage;
    private Image pathImage;
    private Image wallImage;
    private Image playerTanks[];
    private Image scaledPlayerTanks[];
    
    private Image aiTanks[];
    private Image scaledAITanks[];
    
    private GameResources(){
        String basePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        
        this.wonImage = GameResources.getImage(basePath + separator + "images" + separator + "YouWon.gif");
        this.lostImage = GameResources.getImage(basePath + separator + "images" + separator + "YouLost.gif");
        
        this.pathImage = GameResources.getImage(basePath + separator + "images" + separator + "path.png");
        this.wallImage = GameResources.getImage(basePath + separator + "images" + separator + "wall.png");
        this.playerTanks = new Image[4];
        this.scaledPlayerTanks = new Image[4];
        
        this.aiTanks = new Image[4];
        this.scaledAITanks = new Image[4];
        
        this.playerTanks[GameFigure.DIRECTION_LEFT] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Left.gif");
        this.playerTanks[GameFigure.DIRECTION_RIGHT] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Right.gif");
        this.playerTanks[GameFigure.DIRECTION_FORWARD] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Forward.gif");
        this.playerTanks[GameFigure.DIRECTION_BACKWARD] = GameResources.getImage(basePath + separator + "images" + separator + "Tank_Backward.gif");
        
        this.scaledPlayerTanks[GameFigure.DIRECTION_LEFT] = this.playerTanks[GameFigure.DIRECTION_LEFT];
        this.scaledPlayerTanks[GameFigure.DIRECTION_RIGHT] = this.playerTanks[GameFigure.DIRECTION_RIGHT];
        this.scaledPlayerTanks[GameFigure.DIRECTION_FORWARD] = this.playerTanks[GameFigure.DIRECTION_FORWARD];
        this.scaledPlayerTanks[GameFigure.DIRECTION_BACKWARD] = this.playerTanks[GameFigure.DIRECTION_BACKWARD];
        
        this.aiTanks[GameFigure.DIRECTION_LEFT] = GameResources.getImage(basePath + separator + "images" + separator + "AI_Tank_Left.gif");
        this.aiTanks[GameFigure.DIRECTION_RIGHT] = GameResources.getImage(basePath + separator + "images" + separator + "AI_Tank_Right.gif");
        this.aiTanks[GameFigure.DIRECTION_FORWARD] = GameResources.getImage(basePath + separator + "images" + separator + "AI_Tank_Forward.gif");
        this.aiTanks[GameFigure.DIRECTION_BACKWARD] = GameResources.getImage(basePath + separator + "images" + separator + "AI_Tank_Backward.gif");
        
        this.scaledAITanks[GameFigure.DIRECTION_LEFT] = this.aiTanks[GameFigure.DIRECTION_LEFT];
        this.scaledAITanks[GameFigure.DIRECTION_RIGHT] = this.aiTanks[GameFigure.DIRECTION_RIGHT];
        this.scaledAITanks[GameFigure.DIRECTION_FORWARD] = this.aiTanks[GameFigure.DIRECTION_FORWARD];
        this.scaledAITanks[GameFigure.DIRECTION_BACKWARD] = this.aiTanks[GameFigure.DIRECTION_BACKWARD];
    }
    
    public Image getWonImage(){
        return this.wonImage;
    }
    
    public Image getLostImage(){
        return this.lostImage;
    }
    
    public Image getPathImage(){
        return this.pathImage;
    }
    
    public Image getWallImage(){
        return this.wallImage;
    }
    
    public Image getPlayerTankImage(int direction){
        return this.scaledPlayerTanks[direction];
    }
    
    public Image getAITankImage(int direction){
        return this.scaledAITanks[direction];
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

    @Override
    public void fieldLoaded(Field field) {
        this.scaledPlayerTanks = new Image[this.playerTanks.length];
        for(int i = 0; i < this.playerTanks.length; i++){
            this.scaledPlayerTanks[i] = this.playerTanks[i].getScaledInstance(field.tankSize, field.tankSize, Image.SCALE_DEFAULT);
        }
        
        this.scaledAITanks = new Image[this.aiTanks.length];
        for(int i = 0; i < this.aiTanks.length; i++){
            this.scaledAITanks[i] = this.aiTanks[i].getScaledInstance(field.tankSize, field.tankSize, Image.SCALE_DEFAULT);
        }
    }
}
