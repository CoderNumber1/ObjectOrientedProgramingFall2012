
import java.util.Iterator;

@GameMetadata(displayName = "Tic Tac Toe")
public class TicTacToe implements IGame,  Iterable{
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public TicTacToe(){
        this.ticTacToeBoard = new TicTacToeMove[3][3];
        this.ai = new TicTacToeAI(this, TicTacToe.PLAYER_X_MARK);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Commands">
    @Override
    public void startGame(){
        this.gameWon = false;
        this.winningPlayer = ' ';
        
        for(int r = 0; r < this.ticTacToeBoard.length; r++){
            for(int c = 0; c < this.ticTacToeBoard[r].length; c++){
                this.ticTacToeBoard[r][c] = new TicTacToeMove(r, c, ' ');
            }
        }
    }
    
    @Override
    public void move(String guess) {
        this.moveCount++;
        this.move(guess, TicTacToe.PLAYER_O_MARK);
        
        this.evaluateWinner();
            
        if(!this.gameWon && this.doesValidMoveExist()){
            Move aiMove = ai.determineMove();

            if(aiMove != null){
                this.move(aiMove.getRow(), aiMove.getColumn(), TicTacToe.PLAYER_X_MARK);
                this.evaluateWinner();
            }
        }
        
        this.evaluateWinner();
    }
    
    public void move(String move, char mark){
        String[] rowColumn = move.split(" ");
        
        this.move(Integer.parseInt(rowColumn[0]), Integer.parseInt(rowColumn[1]), mark);
    }
    
    public void move(int row, int column, char mark){
        Move targetMove = this.getMoveAt(row, column);
        
        if(!targetMove.moveTaken()){
            targetMove.setMark(mark);
        }
    }
    
    public void evaluateWinner(){
        char winner = ' ';
        int markCount = 0;
        
        //Evaluate horizontal winner
        row: for(int r = 0; r < this.ticTacToeBoard.length; r++){
            winner = ' ';
            markCount = 0;
            
            for(int c = 0; c < this.ticTacToeBoard[r].length; c++){
                char mark = this.ticTacToeBoard[r][c].getMark();
                
                if(winner == ' '){
                    winner = mark;
                    markCount = 1;
                }
                else if(winner != mark){
                    winner = ' ';
                    markCount = 0;
                    continue row;
                }
                else{
                    markCount++;
                }
            }
            
            if(winner != ' ' && markCount == 3){
                this.gameWon = true;
                this.winningPlayer = winner;
                return;
            }
        }
        
        //Evaluate a vertical winner
        column: for(int c = 0; c < this.ticTacToeBoard[0].length; c++){
            winner = ' ';
            markCount = 0;

            for(int r = 0; r < this.ticTacToeBoard.length; r++){
                char mark = this.ticTacToeBoard[r][c].getMark();

                if(winner == ' '){
                    winner = mark;
                    markCount = 1;
                }
                else if(winner != mark){
                    winner = ' ';
                    markCount = 0;
                    continue column;
                }
                else{
                    markCount++;
                }
            }

            if(winner != ' ' && markCount == 3){
                this.gameWon = true;
                this.winningPlayer = winner;
                return;
            }
        }
        
        //Evaluate a diagnonal left winner
        if(this.ticTacToeBoard[0][0].moveTaken() && this.ticTacToeBoard[0][0].getMark() == this.ticTacToeBoard[1][1].getMark() && this.ticTacToeBoard[0][0].getMark() == this.ticTacToeBoard[2][2].getMark()){
            this.gameWon = true;
            this.winningPlayer = this.ticTacToeBoard[0][0].getMark();
            return;
        }
        
        //Evaluate a diagnonal right winner
        if(this.ticTacToeBoard[0][2].moveTaken() && this.ticTacToeBoard[0][2].getMark() == this.ticTacToeBoard[1][1].getMark() && this.ticTacToeBoard[0][2].getMark() == this.ticTacToeBoard[2][0].getMark()){
            this.gameWon = true;
            this.winningPlayer = this.ticTacToeBoard[0][2].getMark();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Queries">
    public TicTacToeMove getMoveAt(int row, int column){
        if(this.doesMoveExist(row, column)){
            return this.ticTacToeBoard[row][column];
        }
        else{
            return null;
        }
    }
    
    public boolean doesValidMoveExist(){
        Iterator moveIterator = this.iterator();
        
        while(moveIterator.hasNext()){
            if(!((Move)moveIterator.next()).moveTaken()){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean doesMoveExist(int row, int column){
        return (row < this.ticTacToeBoard.length && row >= 0) && (column < this.ticTacToeBoard[0].length && column >= 0);
    }
    
    @Override
    public boolean validateInput(String guess) {
        return this.isMoveValid(guess);
    }
    
    public boolean isMoveValid(String move){
        if(move.length() > 3){
            return false;
        }
        
        String[] rowColumn = move.split(" ");
        
        if(rowColumn.length != 2){
            return false;
        }
        
        try{
            int row = Integer.parseInt(rowColumn[0]);
            int column = Integer.parseInt(rowColumn[1]);
            
            return this.isMoveValid(row, column);
        }
        catch(NumberFormatException ex){
            return false;
        }
    }
    
    public boolean isMoveValid(int row, int column){
        return this.doesMoveExist(row, column) && !this.ticTacToeBoard[row][column].moveTaken();
    }
    
    @Override
    public boolean isGameWon(){
        return this.gameWon || !this.doesValidMoveExist();
    }
    
    public char getWinner(){
        return this.winningPlayer;
    }
    
    public boolean didPlayerWin(char playerMark){
        return this.gameWon ? this.winningPlayer == playerMark : false;
    }
    
    @Override
    public String getGameStatus() {
        String divider = "+---+---+---+";
        StringBuffer result = new StringBuffer();
        
        result.append(divider);
        for(int r = 0; r < this.ticTacToeBoard.length; r++){
            result.append("\n|");
            
            for(int c = 0; c < this.ticTacToeBoard[r].length; c++){
                result.append(String.format(" %s |", this.ticTacToeBoard[r][c].getMark()));
            }
            
            result.append('\n');
            result.append(divider);
        }
        
        if(this.gameWon){
            if(this.didPlayerWin(TicTacToe.PLAYER_O_MARK)){
                result.append("You WON!!!");
            }
            else{
                result.append("The AI bested you.");
            }
        }
        else if(!this.doesValidMoveExist()){
            result.append("It was a tie.");
        }
        
        return result.toString();
    }
    
    @Override
    public String getGameInstruction() {
        return String.format("Your turn - where (row col)? ");
    }
    
    @Override
    public String getGameDisplayName() {
        return "Tic Tac Toe";
    }
    
    @Override
    public int getScore() {
        return this.gameWon && this.didPlayerWin(TicTacToe.PLAYER_O_MARK) ? 100 - (this.moveCount > 3 ? this.moveCount * 5 : 0) : 0;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    @Override
    public Iterator iterator(){
        return new TicTacToeIterator(this.ticTacToeBoard);
    }
    
    public TicTacToeMove[][] getBoard(){
        return this.ticTacToeBoard;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private TicTacToeMove[][] ticTacToeBoard;
    private TicTacToeAI ai;
    private boolean gameWon;
    private char winningPlayer;
    private int moveCount;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    public static final char PLAYER_O_MARK = 'O';
    public static final char PLAYER_X_MARK = 'X';
    // </editor-fold>
}