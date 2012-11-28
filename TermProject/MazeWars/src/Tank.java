
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Tank extends Rectangle.Double implements GameFigure {
    protected int state = GameFigure.STATE_STATIONARY;
    protected int direction;
    protected boolean findNearestCell;
    protected boolean fireWeapon;
    protected int skipMove;
    protected int skipFire;
    
    public Tank(){
    }
    
    public void setStart(Point start){
        super.setFrame(start.x * GameData.getInstance().field.sectionWidth
                , start.y * GameData.getInstance().field.sectionHeight
                , GameData.getInstance().field.tankSize
                , GameData.getInstance().field.tankSize);
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
    
    public Image getImage(){
        return GameResources.getInstance().getPlayerTankImage(this.direction);
    }
    
    @Override
    public void render(Graphics g) {
        super.setFrame(x, y, GameData.getInstance().field.tankSize, GameData.getInstance().field.tankSize);
        Image image = this.getImage();
        
        g.drawImage(image, (int)super.x, (int)super.y, null);
    }

    @Override
    public void update() {
        this.updateLocation();
        
        this.updateFireWeapon();
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
            
            if(GameData.getInstance().field.isMovePossible(nextCorner.x, nextCorner.y, this.direction, (int)this.width, (int)this.height)){
                super.setFrame(nextCorner.x, nextCorner.y, GameData.getInstance().field.tankSize, GameData.getInstance().field.tankSize);
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
            result.x = (int)Math.floor(super.x/GameData.getInstance().field.sectionWidth) * GameData.getInstance().field.sectionWidth;
            result.y = (int)Math.floor(super.y/GameData.getInstance().field.sectionHeight) * GameData.getInstance().field.sectionHeight;
            
            this.findNearestCell = false;
            this.skipMove = 5;
            
            return result;
        }
        
        switch(this.direction){
            case GameFigure.DIRECTION_LEFT:
                result.setLocation((int)super.x - GameData.getInstance().field.tankSpeed, (int)super.y);
                break;
            case GameFigure.DIRECTION_RIGHT:
                result.setLocation((int)super.x + GameData.getInstance().field.tankSpeed, (int)super.y);
                break;
            case GameFigure.DIRECTION_FORWARD:
                result.setLocation((int)super.x, (int)super.y - GameData.getInstance().field.tankSpeed);
                break;
            case GameFigure.DIRECTION_BACKWARD:
                result.setLocation((int)super.x, (int)super.y + GameData.getInstance().field.tankSpeed);
                break;
        }
        
        return result;
    }
    
    public void updateFireWeapon(){
        if(this.fireWeapon && this.skipFire <= 0){
            int missleX = -1, missleY=-1;
            
            switch(this.direction){
                case GameFigure.DIRECTION_RIGHT:
                    missleX = (int)(this.x + this.width);
                    missleY = (int)(this.y + this.height/2);
                    break;
                case GameFigure.DIRECTION_LEFT:
                    missleX = (int)this.x;
                    missleY = (int)(this.y + this.height/2);
                    break;
                case GameFigure.DIRECTION_FORWARD:
                    missleX = (int)(this.x + this.width/2);
                    missleY = (int)(this.y);
                    break;
                case GameFigure.DIRECTION_BACKWARD:
                    missleX = (int)(this.x + this.width/2);
                    missleY = (int)(this.y + this.height);
                    break;
            }
            
            GameData.getInstance().fireMissle(missleX, missleY, this.direction, null);
            this.ceaseFire();
            this.skipFire = 5;
        }
        else if(this.fireWeapon){
            this.skipFire--;
        }
    }
    
    public void fireWeapon(){
        this.fireWeapon = true;
    }
    
    public void ceaseFire(){
        this.fireWeapon = false;
    }
}
