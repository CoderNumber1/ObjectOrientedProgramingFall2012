
import java.util.ArrayList;
import java.util.Iterator;

public class TicTacToeIterator implements Iterator {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public TicTacToeIterator(TicTacToeMove[][] moves){
        this.position = 0 ;
        this.moves = new ArrayList<>();
        
        for(int r = 0; r < moves.length; r++){
            for(int c = 0; c < moves[r].length; c++){
                this.moves.add(moves[r][c]);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Queries">
    @Override
    public boolean hasNext(){
        return this.position < this.moves.size();
    }
    
    @Override
    public Object next(){
        if(!this.hasNext()){
            return null;
        }
        
        Object result = this.moves.get(this.position++);
        
        return result;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Commands">
    @Override
    public void remove(){
        //The tic tac toe board is read only.
        //  You can't remove places from the board.
        throw new UnsupportedOperationException();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private int position;
    private ArrayList<TicTacToeMove> moves;
    // </editor-fold>
}
