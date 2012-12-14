
import java.awt.Image;
import java.awt.Point;
import java.util.Stack;

public class AITank extends Tank {
    private AIStrategy strategy;
    private boolean destroyed;
    private int reSpawnWait;
    private static final int POLL_INTERVAL = 20;
    private int pollTimer = 0;
    private AIStrategy.StrategyMove currentMove;
//    private Stack instructions;
//    private PathPoint currentInstruction;
    
    public AITank(){
        super();
    }
    
    public void setStrategy(AIStrategy strategy){
        this.strategy = strategy;
    }
    
    @Override
    public Image getImage(){
        return GameResources.getInstance().getAITankImage(super.direction);
    }
    
    @Override
    public void update(){
        if(super.state != GameFigure.STATE_EXPLODING){
            AIStrategy.StrategyMove move = this.strategy.getNextMove();

            this.currentMove = move;
            super.fireWeapon = this.currentMove.fire;
            super.skipFire = 0;
            super.skipMove = 0;

            this.state = GameFigure.STATE_MOVING;
            //Do some pre-lim stuff here.
            super.update();
        }
    }

    @Override
    public Point getNextCornerLocation() {
        Field field = GameData.getInstance().field;
        Point result = new Point((int)super.x, (int)super.y);
        Point target = this.getCurrentTarget();
        
        target = GameData.getInstance().field.getCellCorner(target.x, target.y);
        
        if(this.currentMove.move){
            if(result.x < target.x){
                result.x += this.currentMove.moveIncrement;

                if(result.x > target.x){
                    result.x = target.x;
                }
            }
            else if(result.x > target.x){
                result.x -= this.currentMove.moveIncrement;

                if(result.x < target.x){
                    result.x = target.x;
                }
            }
            else if(result.y < target.y){
                result.y += this.currentMove.moveIncrement;

                if(result.y > target.y){
                    result.y = target.y;
                }
            }
            else if(result.y > target.y){
                result.y -= this.currentMove.moveIncrement;

                if(result.y < target.y){
                    result.y = target.y;
                }
            }
        }
        
        if(result.x > super.x){
            this.direction = GameFigure.DIRECTION_RIGHT;
        }
        else if(result.x < super.x){
            this.direction = GameFigure.DIRECTION_LEFT;
        }
        else if(result.y < super.y){
            this.direction = GameFigure.DIRECTION_FORWARD;
        }
        else if(result.y > super.y){
            this.direction = GameFigure.DIRECTION_BACKWARD;
        }
        
        return result;
    }
    
//    public Point getTargetLocation(){
//        Point result = new Point();
//        
//        if(this.currentMove != null && this.currentMove.target != null){
//            result.x = GameData.getInstance().field.sectionWidth * this.currentMove.target.x;
//            result.y = GameData.getInstance().field.sectionHeight * this.currentMove.target.y;
//        }
//        else{
//            result.x = (int)super.x;
//            result.y = (int)super.y;
//        }
////        result.x = (int)Math.floor(super.x/GameData.getInstance().field.sectionWidth) * GameData.getInstance().field.sectionWidth;
////        result.y = (int)Math.floor(super.y/GameData.getInstance().field.sectionHeight) * GameData.getInstance().field.sectionHeight;
//        
//        return result;
//    }
    
    public PathPoint getCurrentTarget(){
        if(this.currentMove == null){
            return null;
        }
        return this.currentMove.target;
    }
}
