
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class ArcadeIterator implements Iterator<GameSelection> {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ArcadeIterator(ArrayList<Class<IGame>> games){
        this.position = 0;
        this.games = new ArrayList<>();
        
        for(int i = 0; i < games.size(); i++){
            Class game = games.get(i);
            GameMetadata metadata = (GameMetadata)game.getAnnotation(GameMetadata.class);
            
            this.games.add(new GameSelection(i, metadata != null ? metadata.displayName() : ((Class)game).getName()));
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    @Override
    public boolean hasNext(){
        return this.position < this.games.size();
    }
    
    @Override
    public GameSelection next(){
        if(!this.hasNext()){
            return null;
        }
        
        GameSelection result = this.games.get(this.position++);
        
        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commands">
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private int position;
    private ArrayList<GameSelection> games;
    // </editor-fold>
}
