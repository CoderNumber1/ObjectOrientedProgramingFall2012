
import java.awt.Point;

public class PathPoint extends Point {
    public PathPoint(){
        super();
    }
    public PathPoint(int x, int y){
        super(x, y);
    }
    public PathPoint(Point point){
        super(point.x, point.y);
    }

    @Override
    public boolean equals(Object obj) {
        PathPoint objPoint = (PathPoint)obj;
        
        return objPoint.x == super.x && objPoint.y == super.y;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
