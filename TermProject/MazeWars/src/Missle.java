
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Missle implements GameFigure {
    private int x;
    private int y;
    private int direction;
    private int state;
    private WeaponManager.WeaponType weaponType;
    
    public Missle(int x, int y, int direction, String weaponName){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.state = GameFigure.STATE_MOVING;
        
        if(weaponName != null){
            this.weaponType = WeaponManager.getInstance().getWeaponTypeByName(weaponName);
        }
        else{
            WeaponTypeIterator iterator = WeaponManager.getInstance().getIterator();
            
            while(iterator.hasNext()){
                WeaponManager.WeaponType weapon = iterator.Next();
                
                if(weapon.defaultWeapon){
                    this.weaponType = weapon;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(this.state == GameFigure.STATE_MOVING){
            if(this.weaponType.imageName == null){
                g.setColor(Color.red);
                g.fillOval(x - this.weaponType.size.width/2, y - this.weaponType.size.height/2, this.weaponType.size.width, this.weaponType.size.height);
            }
        }
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
            
            if(GameData.getInstance().field.isMovePossible(nextCorner.x, nextCorner.y, this.direction, this.weaponType.size.width, this.weaponType.size.height)){
                this.x = nextCorner.x;
                this.y = nextCorner.y;
//                super.setFrame(nextCorner.x, nextCorner.y, GameData.getInstance().field.baseMissleRadius * 2, GameData.getInstance().field.baseMissleRadius * 2);
            }
            else{
                this.state = GameFigure.STATE_EXPLODING;
            }
        }
    }
    
    public Point getNextCornerLocation(){
        Point result = new Point(this.x, this.y);
        
        switch(this.direction){
            case GameFigure.DIRECTION_LEFT:
                result.setLocation((int)this.x - this.weaponType.speed, (int)this.y);
                break;
            case GameFigure.DIRECTION_RIGHT:
                result.setLocation((int)this.x + this.weaponType.speed, (int)this.y);
                break;
            case GameFigure.DIRECTION_FORWARD:
                result.setLocation((int)this.x, (int)this.y - this.weaponType.speed);
                break;
            case GameFigure.DIRECTION_BACKWARD:
                result.setLocation((int)this.x, (int)this.y + this.weaponType.speed);
                break;
        }
        
        return result;
    }
}
