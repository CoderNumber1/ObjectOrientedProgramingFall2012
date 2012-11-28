
import java.awt.Point;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Field{
    ArrayList<FieldSection> field;
    HashMap<FieldSection, PathTable> pathTables;
    private FieldReader reader;
    boolean isFieldLoaded;
    
    public Field(){
    }
    
    public void loadField(String fieldName){
            this.pathTables = new HashMap<FieldSection, PathTable>();
            this.reader = new FieldReader(fieldName);
            this.field = new ArrayList<FieldSection>();
            Point start = reader.getStart();
            
            this.field.add(new FieldSection(start.x, start.y));

            ArrayList<HorizontalRun> hRuns = reader.getHorizontalRuns();
            for(HorizontalRun run : hRuns){
                for(int i = run.xBegin; i <= run.xEnd; i++){
                    FieldSection section = new FieldSection(i, run.y);
                    
                    if(!this.field.contains(section)){
                        this.field.add(section);
                    }
                }
            }

            ArrayList<VerticalRun> vRuns = reader.getVerticalRuns();
            for(VerticalRun run : vRuns){
                for(int i = run.yBegin; i <= run.yEnd; i++){
                    FieldSection section = new FieldSection(run.x, i);
                    
                    if(!this.field.contains(section)){
                        this.field.add(section);
                    }
                }
            }

        this.isFieldLoaded = true;
    }
    
    public void findAllPossiblePaths(){
        for(FieldSection origin : this.field){
            this.buildPathTable(origin);
        }
    }
    
    public ArrayList<FieldSection> buildAdjacencyList(FieldSection section){
        ArrayList<FieldSection> result = new ArrayList<FieldSection>();
        
        FieldSection left = new FieldSection(section.x - 1, section.y);
        FieldSection right = new FieldSection(section.x + 1, section.y);
        FieldSection up = new FieldSection(section.x, section.y - 1);
        FieldSection down = new FieldSection(section.x, section.y + 1);
        
        if(this.field.contains(left)){
            result.add(left);
        }
        
        if(this.field.contains(right)){
            result.add(right);
        }
        
        if(this.field.contains(up)){
            result.add(up);
        }
        
        if(this.field.contains(down)){
            result.add(down);
        }
        
        return result;
    }
    
    public void buildPathTable(FieldSection section){
        PathTable table = new PathTable(this.field);
        ArrayBlockingQueue<FieldSection> pathQueue = new ArrayBlockingQueue<FieldSection>(this.field.size());
        
        try {
            pathQueue.put(section);
        } catch (InterruptedException ex) {
            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(!pathQueue.isEmpty()){
            FieldSection vertice = null;
            
            try {
                if(pathQueue.peek() != null){
                    vertice = pathQueue.take();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(vertice != null){
                PathTable.PathTableEntry entry = table.paths.get(vertice);
                entry.known = true;
                
                if(vertice.equals(section)){
                    entry.distance = 0;
                    entry.path = null;
                }
                
                ArrayList<FieldSection> adjacent = this.buildAdjacencyList(vertice);
                
                for(FieldSection adjVertice : adjacent){
                    PathTable.PathTableEntry adjEntry = table.paths.get(adjVertice);
                    
                    if(adjEntry.distance < 0){
                        adjEntry.distance = entry.distance + 1;
                        adjEntry.path = vertice;
                        
                        try {
                            pathQueue.put(adjVertice);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        
        this.pathTables.put(section, table);
    }
    
    public boolean getIsFieldLoaded(){
        return this.isFieldLoaded;
    }
    
    public Point getStartPoint(){
        return this.reader.getStart();
    }
    
    public Point getEndPoint(){
        return this.reader.getEnd();
    }
    
    public FieldReader getFieldReader(){
        return this.reader;
    }
}
