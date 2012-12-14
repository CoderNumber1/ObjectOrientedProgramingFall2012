
import java.awt.Point;
import java.util.Stack;

public class AIStrategy {
    private AITank tank;
    private String strategyName;
    private boolean haveLOS;
    private AIPathManager pathManager;
    private AIStrategyManager strategyManager;
    private TargetStrategy targetStrategy;
    private AIStrategyManager.TargetStrategyConfig targetConfig;
    private AIStrategyManager.FireStrategyConfig fireConfig;
    private AIStrategyManager.MoveStrategyConfig moveConfig;
    
    private int fireWaitTime;
    private int moveWaitTime;
    
    private int fireExecutionWaitTime;
    private int moveExecutionWaitTime;
    
    public AIStrategy(AITank tank, String strategyName, AIPathManager pathManager, AIStrategyManager strategyManager){
        this.tank = tank;
        this.strategyName = strategyName;
        this.pathManager = pathManager;
        this.strategyManager = strategyManager;
        
        this.reloadStrategy(false);
    }
    
    public void reloadStrategy(boolean haveLOS){
        this.moveConfig = this.strategyManager.getMoveStrategyConfig(this.strategyName, haveLOS);
        this.targetConfig = this.strategyManager.getTargetStrategyConfig(this.strategyName, haveLOS);
        this.fireConfig = this.strategyManager.getFireStrategyConfig(this.strategyName, haveLOS);
        
        this.fireExecutionWaitTime = this.fireConfig.executionWaitTime;
        this.moveExecutionWaitTime = this.moveConfig.executionWaitTime;
        
        if(this.targetConfig.target.equals(AIStrategyManager.TARGET_HOME)){
            this.targetStrategy = new StartPointTargetStrategy(this);
        }
        else if(this.targetConfig.target.equals(AIStrategyManager.TARGET_PLAYER)){
            this.targetStrategy = new DirectTargetStrategy(this);
        }
    }
    
    public StrategyMove getNextMove(){
        StrategyMove result = new StrategyMove();
        
        result.haveLOS = this.getLineOfSight();
        
        if(result.haveLOS != this.haveLOS){
            this.reloadStrategy(result.haveLOS);
            this.haveLOS = result.haveLOS;
        }
        
        result.target = this.targetStrategy.getNextTarget();
        
        Point aiLocation = this.tank.getCurrentCellLocation();
        Point playerLocation = GameData.getInstance().tank.getCurrentCellLocation();
        
//        if(result.target.x > aiLocation.x){
//            result.direction = GameFigure.DIRECTION_RIGHT;
//        }
//        else if(result.target.x < aiLocation.x){
//            result.direction = GameFigure.DIRECTION_LEFT;
//        }
//        else if(result.target.y > aiLocation.y){
//            result.direction = GameFigure.DIRECTION_BACKWARD;
//        }
//        else if(result.target.y < aiLocation.y){
//            result.direction = GameFigure.DIRECTION_FORWARD;
//        }
//        else{
//            result.direction = this.tank.direction;
//        }
        
        result.fire = false;
        if(this.fireExecutionWaitTime <= 0){
            if(this.fireConfig.fire && result.haveLOS){
                if(this.fireWaitTime <= 0){
                    if(Math.abs(aiLocation.x - playerLocation.x) >= this.fireConfig.minFireRange
                            || Math.abs(aiLocation.y - playerLocation.y) <= this.fireConfig.maxFireRange){
                        result.fire = true;
                    }

                    this.fireWaitTime = this.fireConfig.waitToFire;
                }
                else{
                    this.fireWaitTime--;
                }
            }
        }
        else{
            this.fireExecutionWaitTime--;
        }
        
        result.move = false;
        if(this.moveExecutionWaitTime <= 0){
            if(this.moveWaitTime <= 0){
                if(this.moveConfig.requiresLOS && !result.haveLOS){
                    result.move = false;
                }
                else{
                    result.move = true;
                    result.moveIncrement = this.moveConfig.moveIncrement;
                    this.moveWaitTime = this.moveConfig.framesPerMove;
                }
            }
            else{
                this.moveWaitTime--;
            }
        }
        else{
            this.moveExecutionWaitTime--;
        }
        
        return result;
    }
    
    public boolean getLineOfSight(){
        Point playerCenter = GameData.getInstance().tank.getCenter();
        Point aiCenter = this.tank.getCenter();
        Field field = GameData.getInstance().field;
        
        Point playerCell = field.getCellLocation(playerCenter);
        Point aiCell = field.getCellLocation(aiCenter);
        
        if(playerCell.x == aiCell.x){
            int yMin = playerCell.y < aiCell.y ? playerCell.y : aiCell.y;
            int yMax = playerCell.y > aiCell.y ? playerCell.y : aiCell.y;
            
            for(; yMin <= yMax; yMin++){
                if(field.isPointInWall(playerCell.x, yMin, true)){
                    return false;
                }
            }
            
            return true;
        }
        else if(playerCell.y == aiCell.y){
            int xMin = playerCell.x < aiCell.x ? playerCell.x : aiCell.x;
            int xMax = playerCell.x > aiCell.x ? playerCell.x : aiCell.x;
            
            for(; xMin <= xMax; xMin++){
                if(field.isPointInWall(xMin, playerCell.y, true)){
                    return false;
                }
            }
            
            return true;
        }
        
        return false;
//        
//        if(playerCenter.x != aiCenter.x && playerCenter.y != aiCenter.y){
//            int x1, x2, y1, y2;
//            if(playerCenter.y > aiCenter.y){
//                x1 = playerCenter.x;
//                y1 = playerCenter.y;
//                
//                x2 = aiCenter.x;
//                y2 = aiCenter.y;
//            }
//            else{
//                x2 = playerCenter.x;
//                y2 = playerCenter.y;
//                
//                x1 = aiCenter.x;
//                y1 = aiCenter.y;
//            }
//            
//            int rise = y1 - y2;
//            int run = x1 - x2;
//            double slope = (double)rise/run;
//            double intercept = (double)y1 / (x1 * slope);
//            
//            int xMin = x1 < x2 ? x1 : x2;
//            int xMax = x1 > x2 ? x1 : x2;
//            
//            for(; xMin <= xMax; xMin++){
//                double y = slope * xMin + intercept;
//                
//                if(y > 0 && y <= GameData.getInstance().field.getPixelHeight() && field.isPointInWall(xMin, y)){
//                    return false;
//                }
//            }
//        }
//        else if(playerCenter.x == aiCenter.x){
//            int yMin = playerCenter.y < aiCenter.y ? playerCenter.y : aiCenter.y;
//            int yMax = playerCenter.y > aiCenter.y ? playerCenter.y : aiCenter.y;
//            
//            for(; yMin <= yMax; yMin++){
//                if(field.isPointInWall(playerCenter.x, yMin)){
//                    return false;
//                }
//            }
//        }
//        else if(playerCenter.y == aiCenter.y){
//            int xMin = playerCenter.x < aiCenter.x ? playerCenter.x : aiCenter.x;
//            int xMax = playerCenter.x > aiCenter.x ? playerCenter.x : aiCenter.x;
//            
//            for(; xMin <= xMax; xMin++){
//                if(field.isPointInWall(xMin, playerCenter.y)){
//                    return false;
//                }
//            }
//        }
//        
//        return true;
    }
    
    public class StrategyMove{
        public PathPoint target;
        public int moveIncrement;
        public boolean move;
        public boolean fire;
        public boolean haveLOS;
    }
    
    public class StartPointTargetStrategy implements TargetStrategy{
        private AIStrategy strategy;
        private int refreshTime;
        private Stack instructions;
        
        public StartPointTargetStrategy(AIStrategy strategy){
            this.strategy = strategy;
        }
        
        @Override
        public void analyzeStrategy(){
            PathPoint target = new PathPoint(this.strategy.tank.getStart());
            PathPoint tankStart = new PathPoint(this.strategy.tank.getCurrentCellLocation());
            
            this.instructions = this.strategy.pathManager.getDirections(tankStart, target);
        }

        @Override
        public PathPoint getNextTarget() {
            AITank tank = this.strategy.tank;
            Field field = GameData.getInstance().field;
            AIStrategyManager.TargetStrategyConfig config = this.strategy.targetConfig;
//            Point currentTarget = tank.getTargetLocation();
            PathPoint target = tank.getCurrentTarget();
            Point tankCell = tank.getCurrentCellLocation();
            
            Point targetCoord;
            if(target != null){
                targetCoord = field.getCellCorner(target.x, target.y);
            }
            else{
                targetCoord = new Point((int)tank.x, (int)tank.y);
            }
            
            if(this.instructions == null){
                this.analyzeStrategy();
            }
            
            if(target == null || (targetCoord.x == tank.x && targetCoord.y == tank.y)){
                if(this.instructions.isEmpty() || this.instructions.size() <= this.strategy.targetConfig.proximity){
                   target = target != null ? new PathPoint(target) : new PathPoint(tankCell.x, tankCell.y);
                }
                else{
                   target = (PathPoint)this.instructions.pop();
                }   
            }
            
            return target;
        }
    }
    
    public class DirectTargetStrategy implements TargetStrategy{
        private AIStrategy strategy;
        private int refreshTime;
        private Stack instructions;
        
        public DirectTargetStrategy(AIStrategy strategy){
            this.strategy = strategy;
        }
        
        @Override
        public void analyzeStrategy(){
            PathPoint target = new PathPoint(GameData.getInstance().tank.getCurrentCellLocation());
            PathPoint tankStart = new PathPoint(this.strategy.tank.getCurrentCellLocation());
            
            this.instructions = this.strategy.pathManager.getDirections(tankStart, target);
            this.refreshTime = this.strategy.targetConfig.refreshTime;
        }
        
        @Override
        public PathPoint getNextTarget() {
            AITank tank = this.strategy.tank;
            Field field = GameData.getInstance().field;
            AIStrategyManager.TargetStrategyConfig config = this.strategy.targetConfig;
//            Point currentTarget = tank.getTargetLocation();
            PathPoint target = tank.getCurrentTarget();
            Point tankCell = tank.getCurrentCellLocation();
            
            Point targetCoord;
            if(target != null){
                targetCoord = field.getCellCorner(target.x, target.y);
            }
            else{
                targetCoord = new Point((int)tank.x, (int)tank.y);
            }
            
            if(this.instructions == null || this.refreshTime <= 0){
                this.analyzeStrategy();
            }
            
            if(target == null || (targetCoord.x == tank.x && targetCoord.y == tank.y)){
                if(this.instructions.isEmpty() || this.instructions.size() <= this.strategy.targetConfig.proximity){
                   target = target != null ? new PathPoint(target) : new PathPoint(tankCell.x, tankCell.y);
                }
                else{
                   target = (PathPoint)this.instructions.pop();
                }   
            }
            
            this.refreshTime--;
            
            return target;
        }
    }
}
