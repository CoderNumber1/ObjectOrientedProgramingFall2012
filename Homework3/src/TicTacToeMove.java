public class TicTacToeMove implements Move {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public TicTacToeMove(int row, int column, char mark){
        this.rowCoordinate = row;
        this.columnCordinate = column;
        this.mark = mark;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Queries">
    @Override
    public boolean moveTaken(){
        return this.mark == TicTacToe.PLAYER_O_MARK || this.mark == TicTacToe.PLAYER_X_MARK;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    @Override
    public int getRow(){
        return this.rowCoordinate;
    }
    
    @Override
    public int getColumn(){
        return this.columnCordinate;
    }
    
    @Override
    public char getMark(){
        return this.mark;
    }
    
    /**
     *
     * @param mark
     */
    @Override
    public void setMark(char mark){
        this.mark = mark;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private int rowCoordinate;
    private int columnCordinate;
    private char mark;
    // </editor-fold>
}
