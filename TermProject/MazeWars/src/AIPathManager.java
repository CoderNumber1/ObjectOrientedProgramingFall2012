
import java.awt.Point;
import java.util.HashMap;
import java.util.Stack;
import org.w3c.dom.Element;

public class AIPathManager extends XMLReader implements FieldObserver {
    private HashMap<PathPoint,HashMap<PathPoint,PathPoint>> pathSet;
    
    public AIPathManager(){
        super();
    }
    public AIPathManager(String levelName){
        super();
        
        this.analyzeLevel(levelName);
    }
    
    public void analyzeLevel(){
        this.pathSet = new HashMap<PathPoint,HashMap<PathPoint,PathPoint>>();
        
        for(Element collection : super.getElements("pathcollection")){
            PathPoint key = new PathPoint();
            key.x = Integer.parseInt(super.getElements(collection, new String[]{"key", "x"}).get(0).getTextContent());
            key.y = Integer.parseInt(super.getElements(collection, new String[]{"key", "y"}).get(0).getTextContent());
            
            HashMap<PathPoint,PathPoint> pathTable = new HashMap<PathPoint,PathPoint>();
            for(Element pathTableEntry : super.getElements(collection, new String[]{"pathtable", "pathtableentry"})){
                PathPoint destination = new PathPoint();
                destination.x = Integer.parseInt(super.getElements(pathTableEntry, new String[]{"destination", "x"}).get(0).getTextContent());
                destination.y = Integer.parseInt(super.getElements(pathTableEntry, new String[]{"destination", "y"}).get(0).getTextContent());
                
                PathPoint path = null;
                if(super.getElements(pathTableEntry, new String[]{"path"}).size() > 0){
                    path = new PathPoint();
                    path.x = Integer.parseInt(super.getElements(pathTableEntry, new String[]{"path", "x"}).get(0).getTextContent());
                    path.y = Integer.parseInt(super.getElements(pathTableEntry, new String[]{"path", "y"}).get(0).getTextContent());
                }
                
                pathTable.put(destination, path);
            }
            
            this.pathSet.put(key, pathTable);
        }
    }
    public void analyzeLevel(String levelName){
        String basePath = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        
        super.loadDocument(basePath + separator + "Fields" + separator + levelName + "_Analysis.xml");
        
        this.analyzeLevel();
    }
    
    public Stack getDirections(PathPoint begin, PathPoint end){
        Stack result = new Stack();
        
        HashMap<PathPoint,PathPoint> pathTable = this.pathSet.get(begin);
        
        while(end != null && !end.equals(begin)){
            result.push(end);
            
            end = pathTable.get(end);
        }
        
        return result;
    }

    @Override
    public void fieldLoaded(Field field) {
        this.analyzeLevel(field.fieldName);
    }
}
