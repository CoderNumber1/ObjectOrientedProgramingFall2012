
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class FieldSection extends Rectangle2D.Double implements GameFigure {
//    int x;
//    int y;
    boolean isPath;
    
    public FieldSection(int x, int y, boolean isPath){
        this.x = x;
        this.y = y;
        this.height = GamePanel.SECTION_HEIGHT;
        this.width = GamePanel.SECTION_WIDTH;
        this.isPath = isPath;
    }

    @Override
    public void render(Graphics g) {
        Image image = this.isPath ? GameResources.getInstance().getPathImage() : GameResources.getInstance().getWallImage();
        
        g.drawImage(image, (int)x, (int)y, null);
    }

    @Override
    public void update() {}

    @Override
    public int getState() {
        return GameFigure.STATE_STATIONARY;
    }

    @Override
    public int getDirection() {
        return -1;
    }
    
    public boolean getIsPath(){
        return this.isPath;
    }
}
