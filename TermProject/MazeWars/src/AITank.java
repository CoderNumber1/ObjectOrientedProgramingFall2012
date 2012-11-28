
import java.awt.Image;
import java.awt.Point;
import java.util.Stack;

public class AITank extends Tank {
    private boolean destroyed;
    private int reSpawnWait;
    private Stack instructions;
    private PathPoint currentInstruction;
    
    public AITank(){
        super();
    }
    
    @Override
    public Image getImage(){
        return GameResources.getInstance().getAITankImage(super.direction);
    }
    
    @Override
    public void update(){
        if(this.instructions == null){
            GameData data = GameData.getInstance();
            AIPathManager manager = data.pathManager;
            
            this.instructions = manager.getDirections(new PathPoint(data.field.getStartPoint()), new PathPoint(data.field.getEndPoint()));
        }
        
        Point target = this.getTargetLocation();
        if((this.currentInstruction == null || (super.x == target.x && super.y == target.y)) && !this.instructions.isEmpty()){
            this.currentInstruction = (PathPoint)this.instructions.pop();
        }
        
        if(!this.instructions.empty()){
            this.state = GameFigure.STATE_MOVING;
        }
        else if(this.currentInstruction == null || (super.x == target.x && super.y == target.y)){
            this.state = GameFigure.STATE_STATIONARY;
        }
        
        this.state = GameFigure.STATE_MOVING;
        //Do some pre-lim stuff here.
        super.update();
    }

    @Override
    public Point getNextCornerLocation() {
        Field field = GameData.getInstance().field;
        Point result = new Point((int)super.x, (int)super.y);
        Point target = this.getTargetLocation();
        
        if(result.x < target.x){
            result.x += field.tankSpeed;
            
            if(result.x > target.x){
                result.x = target.x;
            }
        }
        else if(result.x > target.x){
            result.x -= field.tankSpeed;
            
            if(result.x < target.x){
                result.x = target.x;
            }
        }
        else if(result.y < target.y){
            result.y += field.tankSpeed;
            
            if(result.y > target.y){
                result.y = target.y;
            }
        }
        else if(result.y > target.y){
            result.y -= field.tankSpeed;
            
            if(result.y < target.y){
                result.y = target.y;
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
    
    public Point getTargetLocation(){
        Point result = new Point();
        
        if(this.currentInstruction != null){
            result.x = GameData.getInstance().field.sectionWidth * this.currentInstruction.x;
            result.y = GameData.getInstance().field.sectionHeight * this.currentInstruction.y;
        }
//        result.x = (int)Math.floor(super.x/GameData.getInstance().field.sectionWidth) * GameData.getInstance().field.sectionWidth;
//        result.y = (int)Math.floor(super.y/GameData.getInstance().field.sectionHeight) * GameData.getInstance().field.sectionHeight;
        
        return result;
    }
}
