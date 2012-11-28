
import java.util.ArrayList;
import java.util.HashMap;

public class PathTable {
    public PathTable(ArrayList<FieldSection> vertices){
        this.paths = new HashMap<FieldSection, PathTableEntry>();
        
        for(FieldSection vertice : vertices){
            PathTableEntry path = new PathTableEntry();
            path.known = false;
            path.distance = -1;
            path.path = null;
            
            this.paths.put(vertice, path);
        }
    }
    
    HashMap<FieldSection, PathTableEntry> paths;
    
    public class PathTableEntry{
        public boolean known;
        public int distance;
        public FieldSection path;
    }
}
