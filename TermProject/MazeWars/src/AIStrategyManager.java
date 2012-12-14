
import java.util.HashMap;
import org.w3c.dom.Element;

public class AIStrategyManager extends XMLReader implements FieldObserver {
    public static final String TARGET_HOME = "HOME";
    public static final String TARGET_PLAYER = "PLAYER";
    
    private HashMap<String,AIStrategyConfig> aiStrategies;
    private HashMap<String,LOSStrategyConfig> losStrategies;
    private HashMap<String,MoveStrategyConfig> moveStrategies;
    private HashMap<String,TargetStrategyConfig> targetStrategies;
    private HashMap<String,FireStrategyConfig> fireStrategies;
    
    public AIStrategyManager(){
        super();
    }
    public AIStrategyManager(String levelName){
        super();
        
        this.analyzeLevel(levelName);
    }
    
    public void analyzeLevel(){
        this.aiStrategies = new HashMap<String,AIStrategyConfig>();
        this.losStrategies = new HashMap<String,LOSStrategyConfig>();
        this.moveStrategies = new HashMap<String,MoveStrategyConfig>();
        this.targetStrategies = new HashMap<String,TargetStrategyConfig>();
        this.fireStrategies = new HashMap<String,FireStrategyConfig>();
        
        for(Element strategy : super.getElements("aistrategies", "strategy")){
            AIStrategyConfig config = new AIStrategyConfig();
            config.name = super.getElementValue(strategy, "name");
            config.losGainedStrategy = super.getElementValue(strategy, "losgained");
            config.losLostStrategy = super.getElementValue(strategy, "loslost");
            
            this.aiStrategies.put(config.name, config);
        }
        
        for(Element strategy : super.getElements("losstrategies", "strategy")){
            LOSStrategyConfig config = new LOSStrategyConfig();
            config.name = super.getElementValue(strategy, "name");
            config.movingStrategy = super.getElementValue(strategy, "moving");
            config.targetingStrategy = super.getElementValue(strategy, "targeting");
            config.firingStrategy = super.getElementValue(strategy, "firing");
            
            this.losStrategies.put(config.name, config);
        }
        
        for(Element strategy : super.getElements("movementstrategies", "strategy")){
            MoveStrategyConfig config = new MoveStrategyConfig();
            config.name = super.getElementValue(strategy, "name");
            config.moveIncrement = Integer.parseInt(super.getElementValue(strategy, "moveincrement"));
            config.framesPerMove = Integer.parseInt(super.getElementValue(strategy, "framespermove"));
            config.executionWaitTime = Integer.parseInt(super.getElementValue(strategy, "executionwaittime"));
            config.requiresLOS = Boolean.parseBoolean(super.getElementValue(strategy, "requireslostomove"));
            
            this.moveStrategies.put(config.name, config);
        }
        
        for(Element strategy : super.getElements("targetingstrategies", "strategy")){
            TargetStrategyConfig config = new TargetStrategyConfig();
            config.name = super.getElementValue(strategy, "name");
            config.target = super.getElementValue(strategy, "target");
            config.proximity = Integer.parseInt(super.getElementValue(strategy, "proximity"));
            config.refreshTime = Integer.parseInt(super.getElementValue(strategy, "refreshtime"));
            
            this.targetStrategies.put(config.name, config);
        }
            
        for(Element strategy : super.getElements("firestrategies", "strategy")){
            FireStrategyConfig config = new FireStrategyConfig();
            config.name = super.getElementValue(strategy, "name");
            config.fire = Boolean.parseBoolean(super.getElementValue(strategy, "fire"));
            config.maxFireRange = Integer.parseInt(super.getElementValue(strategy, "firerange", "max"));
            config.minFireRange = Integer.parseInt(super.getElementValue(strategy, "firerange", "min"));
            config.waitToFire = Integer.parseInt(super.getElementValue(strategy, "firewait"));
            config.executionWaitTime = Integer.parseInt(super.getElementValue(strategy, "executionwaittime"));
            
            this.fireStrategies.put(config.name, config);
        }
    }
    public void analyzeLevel(String levelName){
        String basePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        
        super.loadDocument(basePath + separator + "Fields" + separator + levelName + "_Strategy.xml");
        
        this.analyzeLevel();
    }

    @Override
    public void fieldLoaded(Field field) {
        this.analyzeLevel(field.fieldName);
    }
    
    public MoveStrategyConfig getMoveStrategyConfig(String aiStrategyName, boolean haveLOS){
        AIStrategyConfig aiStrategy = this.aiStrategies.get(aiStrategyName);
        
        if(haveLOS){
            return this.moveStrategies.get(this.losStrategies.get(aiStrategy.losGainedStrategy).movingStrategy);
        }
        else{
            return this.moveStrategies.get(this.losStrategies.get(aiStrategy.losLostStrategy).movingStrategy);
        }
    }
    
    public TargetStrategyConfig getTargetStrategyConfig(String aiStrategyName, boolean haveLOS){
        AIStrategyConfig aiStrategy = this.aiStrategies.get(aiStrategyName);
        
        if(haveLOS){
            return this.targetStrategies.get(this.losStrategies.get(aiStrategy.losGainedStrategy).targetingStrategy);
        }
        else{
            return this.targetStrategies.get(this.losStrategies.get(aiStrategy.losLostStrategy).targetingStrategy);
        }
    }
    
    public FireStrategyConfig getFireStrategyConfig(String aiStrategyName, boolean haveLOS){
        AIStrategyConfig aiStrategy = this.aiStrategies.get(aiStrategyName);
        
        if(haveLOS){
            return this.fireStrategies.get(this.losStrategies.get(aiStrategy.losGainedStrategy).firingStrategy);
        }
        else{
            return this.fireStrategies.get(this.losStrategies.get(aiStrategy.losLostStrategy).firingStrategy);
        }
    }
    
    public class AIStrategyConfig{
        public String name;
        public String losGainedStrategy;
        public String losLostStrategy;
    }
    
    public class LOSStrategyConfig{
        public String name;
        public String movingStrategy;
        public String targetingStrategy;
        public String firingStrategy;
    }
    
    public class MoveStrategyConfig{
        public String name;
        public int moveIncrement;
        public int framesPerMove;
        public int executionWaitTime;
        public boolean requiresLOS;
    }
    
    public class TargetStrategyConfig{
        public String name;
        public String target;
        public int proximity;
        public int refreshTime;
    }
    
    public class FireStrategyConfig{
        public String name;
        public boolean fire;
        public int minFireRange;
        public int maxFireRange;
        public int waitToFire;
        public int executionWaitTime;
    }
}
