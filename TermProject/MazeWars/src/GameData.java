
import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameData implements FieldWatcherSubject, Iterable<GameFigure> {
    private static final GameData _INSTANCE = new GameData();
    public static final int MODE_PLAY = 1;
    public static final int MODE_LOAD = 2;
    public static final int MODE_WON = 3;
    public static final int MODE_LOST = 4;
    
    int mode;
    
    final Tank tank;
    final List figures;
    final List<Line> lines;
    final Field field;
    AIPathManager pathManager;
    AIStrategyManager strategyManager;
    boolean fieldLoaded;
    
    private ArrayList<FieldObserver> fieldObservers;
    
    public static GameData getInstance(){
        return GameData._INSTANCE;
    }
    private GameData(){
        this.pathManager = new AIPathManager();
        this.strategyManager = new AIStrategyManager();
        this.fieldObservers = new ArrayList<FieldObserver>();
        this.watchForFieldChange(this.strategyManager);
        this.watchForFieldChange(this.pathManager);
        
        this.mode = GameData.MODE_LOAD;
        this.figures = Collections.synchronizedList(new ArrayList<GameFigure>());
        this.lines = Collections.synchronizedList(new ArrayList<Line>());
//        this.figures.add(new Tank(50, 50, Color.blue));
        this.tank = new Tank();
//        this.field = new FieldSection[23][23];
        this.field = new Field();
        this.fieldLoaded = false;
    }
    
    public void update() {
        if(this.mode == GameData.MODE_LOAD){
            if(!this.fieldLoaded){
                synchronized(this.field){
                    this.field.loadField("Level_One");
                }
                
                this.fieldLoaded = true;
                this.tank.setStart(this.field.getStartPoint());
            }
            
            this.mode = GameData.MODE_PLAY;
        }
        else if(this.mode == GameData.MODE_PLAY){
            synchronized (this.tank){
                tank.update();
            }

            List remove = new ArrayList<GameFigure>();
            GameFigure f;
            synchronized (figures) {
                for (int i = 0; i < figures.size(); i++) {
                    f = (GameFigure) figures.get(i);
                    f.update();
                }
                figures.removeAll(remove);
            }
            
            synchronized(this.tank){
                if(this.tank.state == GameFigure.STATE_EXPLODING){
                    this.mode = GameData.MODE_LOST;
                }
                else{
                    synchronized(this.figures){
                        boolean won = true;
                        for(Object figure : this.figures){
                            if(figure instanceof AITank){
                                AITank tank = (AITank)figure;
                                
                                if(tank.state != GameFigure.STATE_EXPLODING){
                                    won = false;
                                }
                            }
                        }
                        
                        if(won){
                            this.mode = GameData.MODE_WON;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void watchForFieldChange(FieldObserver observer) {
        this.fieldObservers.add(observer);
    }

    @Override
    public void signalFieldChange(Field field) {
//        this.strategyManager.analyzeLevel(field.fieldName);
        
        for(FieldObserver observer : this.fieldObservers){
            observer.fieldLoaded(field);
        }
        
        ArrayList<AITank> aiTanks = field.getAITanks();
        for(AITank tank : aiTanks){
            this.figures.add(tank);
        }
    }
    
    public void fireMissle(int x, int y, int direction, String weaponName){
        this.figures.add(new Missle(x, y, direction, weaponName));
    }

    @Override
    public Iterator<GameFigure> getIterator() {
        ArrayList<GameFigure> elements = new ArrayList<GameFigure>();
        
        elements.add(this.field);
        elements.add(this.tank);
        
        for(Object figure : this.figures){
            elements.add((GameFigure)figure);
        }
        
        GameFigureIterator result = new GameFigureIterator(elements);
        
        return result;
    }
    
    public Iterator<GameFigure> getTankIterator(){
        ArrayList<GameFigure> elements = new ArrayList<GameFigure>();
        
        elements.add(this.tank);
        
        for(Object figure : this.figures){
            if(figure instanceof AITank){
                elements.add((AITank)figure);
            }
        }
        
        GameFigureIterator result = new GameFigureIterator(elements);
        
        return result;
    }
}
