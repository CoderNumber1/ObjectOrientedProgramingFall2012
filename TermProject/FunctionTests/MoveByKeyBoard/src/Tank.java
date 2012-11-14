
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Tank extends Rectangle.Double implements GameFigure {
    static double SIZE = 15.0;
    Color color;
    private int state = GameFigure.STATE_STATIONARY;
    private int direction;
    private boolean findNearestCell;
    private int skipMove;
    private static final int UNIT_TRAVEL_DISTANCE = 1;
    private final GameData gameData;
    
    public Tank(int x, int y, Color color, GameData gameData){
        this.gameData = gameData;
       
        super.setFrame(x, y, Tank.SIZE, Tank.SIZE);
        this.color = color;
    }
    
    public void setStart(Start start){
        super.setFrame(start.x * GamePanel.SECTION_WIDTH
                , start.y * GamePanel.SECTION_HEIGHT
                , Tank.SIZE
                , Tank.SIZE);
    }
    
    public void move(int direction){
        this.direction = direction;
        this.state = GameFigure.STATE_MOVING;
    }
    
    public void stopMoving(){
        this.state = GameFigure.STATE_STATIONARY;
    }
    
    public void setFindNearestCell(){
        this.findNearestCell = true;
    }
    
    @Override
    public void render(Graphics g) {
        Image image = GameResources.getInstance().getPlayerTankImage(this.direction);
        
        g.drawImage(image, (int)super.x, (int)super.y, null);
//        g.setColor(this.color);
//        g.fillOval((int)super.x, (int)super.y, (int)super.width, (int)super.height);
    }

    @Override
    public void update() {
        this.updateLocation();
    }

    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public int getDirection() {
        return this.direction;
    }
    
    public void updateLocation(){
        if(this.state == GameFigure.STATE_MOVING){
            Point nextCorner = this.getNextCornerLocation();
            
            if(this.gameData.field.isMovePossible(nextCorner.x, nextCorner.y, this.direction)){
                super.setFrame(nextCorner.x, nextCorner.y, Tank.SIZE, Tank.SIZE);
            }
        }
    }
    
    public Point getNextCornerLocation(){
        Point result = new Point((int)super.x, (int)super.y);
        
        if(this.skipMove > 0){
            this.skipMove--;
            return result;
        }
        
        if(this.findNearestCell){
            result.x = (int)Math.floor(super.x/GamePanel.SECTION_WIDTH) * GamePanel.SECTION_WIDTH;
            result.y = (int)Math.floor(super.y/GamePanel.SECTION_HEIGHT) * GamePanel.SECTION_HEIGHT;
            
            this.findNearestCell = false;
            this.skipMove = 5;
            
            return result;
        }
        
        switch(this.direction){
            case GameFigure.DIRECTION_LEFT:
                result.setLocation((int)super.x - Tank.UNIT_TRAVEL_DISTANCE, (int)super.y);
                break;
            case GameFigure.DIRECTION_RIGHT:
                result.setLocation((int)super.x + Tank.UNIT_TRAVEL_DISTANCE, (int)super.y);
                break;
            case GameFigure.DIRECTION_FORWARD:
                result.setLocation((int)super.x, (int)super.y - Tank.UNIT_TRAVEL_DISTANCE);
                break;
            case GameFigure.DIRECTION_BACKWARD:
                result.setLocation((int)super.x, (int)super.y + Tank.UNIT_TRAVEL_DISTANCE);
                break;
        }
        
        return result;
    }
}
