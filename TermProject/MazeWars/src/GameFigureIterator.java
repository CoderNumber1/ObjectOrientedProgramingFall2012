
import java.util.ArrayList;

public class GameFigureIterator implements Iterator<GameFigure> {
    private final ArrayList<GameFigure> source;
    private int index;
    
    public GameFigureIterator(ArrayList<GameFigure> source){
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.source.size();
    }

    @Override
    public GameFigure Next() {
        GameFigure result = null;
        
        if(this.hasNext()){
            result = this.source.get(index);
            index++;
        }
        
        return result;
    }
}
