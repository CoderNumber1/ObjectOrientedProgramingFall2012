
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameData {
    public static final int MODE_PLAY = 1;
    public static final int MODE_LOAD = 2;
    
    int mode;
    
    final Tank tank;
    final List figures;
    final List<Line> lines;
    final Field field;
//    final FieldSection field[][];
    boolean fieldLoaded;
    
    public GameData(){
        this.mode = GameData.MODE_LOAD;
        this.figures = Collections.synchronizedList(new ArrayList<GameFigure>());
        this.lines = Collections.synchronizedList(new ArrayList<Line>());
//        this.figures.add(new Tank(50, 50, Color.blue));
        this.tank = new Tank(50, 50, Color.blue, this);
//        this.field = new FieldSection[23][23];
        this.field = new Field();
        this.fieldLoaded = false;
    }
    
    public void update() {
        if(this.mode == GameData.MODE_LOAD){
            if(!this.fieldLoaded){
                synchronized(this.field){
                    this.field.loadField("FieldTest1");
//                    FieldReader reader = new FieldReader("FieldTest1");
//                    
//                    Start start = reader.getStart();
//                    int xStart = GamePanel.SECTION_WIDTH * start.x;
//                    int yStart = GamePanel.SECTION_HEIGHT * start.y;
//                    this.field[start.x][start.y] = new FieldSection(xStart, yStart, true);
//                    
//                    ArrayList<HorizontalRun> hRuns = reader.getHorizontalRuns();
//                    for(HorizontalRun run : hRuns){
//                        for(int i = run.xBegin; i <= run.xEnd; i++){
//                            int xOffset = GamePanel.SECTION_WIDTH * i;
//                            int yOffset = GamePanel.SECTION_HEIGHT * run.y;
//                            
//                            this.field[i][run.y] = new FieldSection(xOffset, yOffset, true);
//                        }
//                    }
//                    
//                    ArrayList<VerticalRun> vRuns = reader.getVerticalRuns();
//                    for(VerticalRun run : vRuns){
//                        for(int i = run.yBegin; i <= run.yEnd; i++){
//                            int xOffset = GamePanel.SECTION_WIDTH * run.x;
//                            int yOffset = GamePanel.SECTION_HEIGHT * i;
//                            
//                            this.field[run.x][i] = new FieldSection(xOffset, yOffset, true);
//                        }
//                    }
//                    
//                    for(int x = 0; x < this.field.length; x++){
//                        for(int y = 0; y < this.field[x].length; y++){
//                            if(this.field[x][y] == null){
//                                int xOffset = GamePanel.SECTION_WIDTH * x;
//                                int yOffset = GamePanel.SECTION_HEIGHT * y;
//                                
//                                this.field[x][y] = new FieldSection(xOffset, yOffset, false);
//                            }
//                        }
//                    }
                }
                
                this.fieldLoaded = true;
                this.tank.setStart(this.field.getStartPoint());
            }
            
            this.mode = GameData.MODE_PLAY;
//            synchronized (this.lines){
//                int numHorizontal = GamePanel.HEIGHT / GamePanel.SECTION_HEIGHT + 1;
//                int numVertical = GamePanel.WIDTH / GamePanel.SECTION_WIDTH + 1;
//                
//                int linex1, linex2, liney1, liney2;
//                    Color lineColor = Color.BLACK;
//                
//                if(this.lines.size() < numHorizontal){
//                    linex1 = 0;
//                    linex2 = GamePanel.WIDTH;
//                    liney1 = GamePanel.HEIGHT - this.lines.size() * GamePanel.SECTION_HEIGHT;
//                    liney2 = liney1;
//
//                    this.lines.add(new Line(linex1, liney1, linex2, liney2, lineColor));
//                }
//                else if(this.lines.size() - numHorizontal < numVertical){
//                    liney1 = 0;
//                    liney2 = GamePanel.HEIGHT;
//                    linex1 = GamePanel.WIDTH - (this.lines.size() - numHorizontal) * GamePanel.SECTION_WIDTH;
//                    linex2 = linex1;
//                    
//                    this.lines.add(new Line(linex1, liney1, linex2, liney2, lineColor));
//                }
//                else{
//                    this.mode = GameData.MODE_PLAY;
//                }
//            }
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
    //                if (f.getState() == GameFigure.STATE_DONE) {
    //                    remove.add(f);
    //                }
                }
                figures.removeAll(remove);
            }
        }
    }
}
