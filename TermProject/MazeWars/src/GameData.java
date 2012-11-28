
import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameData implements FieldWatcherSubject {
    private static final GameData _INSTANCE = new GameData();
    public static final int MODE_PLAY = 1;
    public static final int MODE_LOAD = 2;
    
    int mode;
    
    final Tank tank;
    final List figures;
    final List<Line> lines;
    final Field field;
    AIPathManager pathManager;
    boolean fieldLoaded;
    
    private ArrayList<FieldObserver> fieldObservers;
    
    public static GameData getInstance(){
        return GameData._INSTANCE;
    }
    private GameData(){
        this.pathManager = new AIPathManager();
        this.fieldObservers = new ArrayList<FieldObserver>();
        this.watchForFieldChange(pathManager);
        
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
        else{
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
        }
    }

    @Override
    public void watchForFieldChange(FieldObserver observer) {
        this.fieldObservers.add(observer);
    }

    @Override
    public void signalFieldChange(Field field) {
        Point start = field.getStartPoint();
        AITank ai = new AITank();
        
        ai.setStart(start);
        this.figures.add(ai);
        
        for(FieldObserver observer : this.fieldObservers){
            observer.fieldLoaded(field);
        }
    }
    
    public void fireMissle(int x, int y, int direction, String weaponName){
        this.figures.add(new Missle(x, y, direction, weaponName));
    }
}
