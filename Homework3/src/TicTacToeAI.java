
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TicTacToeAI {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public TicTacToeAI(TicTacToe game, char playerMark){
        this.game = game;
        this.mark = playerMark;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commands">
    public Move determineMove(){
        ArrayList<AIMove> availableMoves = new ArrayList<>();
        ArrayList<AIMove> smartMoves = new ArrayList<>();
        Iterator moveIterator = this.game.iterator();
        
        //get all the open moves from the game board
        while(moveIterator.hasNext()){
            Move move = (Move)moveIterator.next();
            
            if(!move.moveTaken()){
                availableMoves.add(new AIMove(move, this.mark));
            }
        }
        
        if(availableMoves.isEmpty()){
            return null;
        }
        
        //map all adjacent moves to two moves out
        for(AIMove aiMove : availableMoves){
            for(int r = -2; r <= 2; r ++){
                for(int c = -2; c <= 2; c ++){
                    if(!(r == 0 && c == 0)){
                        aiMove.setAdjacentMove(2 + r, 2 + c, this.game.getMoveAt(aiMove.getRow() + r, aiMove.getColumn() + c));
                    }
                }
            }
            
            aiMove.evaluateMoveWeight();
        }
        
        Collections.sort(availableMoves);
        Collections.reverse(availableMoves);
        AIMove maxWeightedMove = Collections.max(availableMoves);
        
        for(AIMove aiMove : availableMoves){
            if(aiMove.getWeight() == maxWeightedMove.getWeight()){
                smartMoves.add(aiMove);
            }
            else{
                break; //We only want to pick among the best possible moves.
            }
        }
        
        //if there any available moves, there will always be at least one smart move.
        if(smartMoves.size() == 1){
            //If there is only one smart move, take it.
            return smartMoves.get(0);
        }
        else{
            //If there are multiple "smart" moves, we don't care which one, just pick at random.
            int moveIndex = (int)(Math.random() * smartMoves.size());
            
            return smartMoves.get(moveIndex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public TicTacToe getGame(){
        return this.game;
    }
    
    public void setGame(TicTacToe game){
        this.game = game;
    }
    
    public char getMark(){
        return this.mark;
    }
    
    public void setMark(char mark){
        this.mark = mark;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private TicTacToe game;
    private char mark;
    // </editor-fold>
}
