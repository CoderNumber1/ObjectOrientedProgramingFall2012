
public class AIMove implements MoveDecorator, Comparable<AIMove>{
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public AIMove(Move move, char targetMark){
        this.adjMoves = new Move[5][5];
        
        this.move = move;
        this.targetMark = targetMark;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commands">
    public void evaluateMoveWeight(){
        MoveEvaluation horizontal = this.evaluateDirection(new RowColumnPair(2, 0), new RowColumnPair(2, 1), new RowColumnPair(2, 3), new RowColumnPair(2,4));
        MoveEvaluation vertical = this.evaluateDirection(new RowColumnPair(0,2), new RowColumnPair(1,2), new RowColumnPair(3,2), new RowColumnPair(4,2));
        MoveEvaluation diagLeft = this.evaluateDirection(new RowColumnPair(0,0), new RowColumnPair(1,1), new RowColumnPair(3,3), new RowColumnPair(4,4));
        MoveEvaluation diagRight = this.evaluateDirection(new RowColumnPair(4,0), new RowColumnPair(3,1), new RowColumnPair(1,3), new RowColumnPair(0,4));
        
        int totalBlocks = horizontal.blockCount + vertical.blockCount + diagLeft.blockCount + diagRight.blockCount;
        int totalWins = horizontal.winCount + vertical.winCount + diagLeft.winCount + diagRight.winCount;
        int totalAdjacentPlayerMoves = horizontal.adjacentPlayerCount + vertical.adjacentPlayerCount + diagLeft.adjacentPlayerCount + diagRight.adjacentPlayerCount;
        
        if(totalWins > 0){
            this.weight = 4;
        }
        else if(totalBlocks > 0){
            this.weight = 3;
        }
        else if(totalAdjacentPlayerMoves > 0){
            this.weight = 2;
        }
        else{
            this.weight = 1;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    @Override
    public boolean moveTaken(){
        return this.move.moveTaken();
    }
    
    @Override
    public int compareTo(AIMove move){
        if(this.weight < move.weight){
            return -1;
        }
        else if(this.weight > move.weight){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    private MoveEvaluation evaluateDirection(RowColumnPair outerLeft, RowColumnPair innerLeft, RowColumnPair innerRight, RowColumnPair outerRight){
        MoveEvaluation result = new MoveEvaluation();
        
        AdjacentMoveStatus outerLeftStatus = this.getMoveStatus(outerLeft.row, outerLeft.column);
        AdjacentMoveStatus innerLeftStatus = this.getMoveStatus(innerLeft.row, innerLeft.column);
        AdjacentMoveStatus innerRightStatus = this.getMoveStatus(innerRight.row, innerRight.column);
        AdjacentMoveStatus outerRightStatus = this.getMoveStatus(outerRight.row, outerRight.column);
        
        if(this.isBlock(innerLeftStatus, outerLeftStatus)){
            result.blockCount++;
        }
        else if(this.isWin(innerLeftStatus, outerLeftStatus)){
            result.winCount++;
        }
        
        if(this.isBlock(innerRightStatus, outerRightStatus)){
            result.blockCount++;
        }
        else if(this.isWin(innerRightStatus, outerRightStatus)){
            result.winCount++;
        }
        
        if(this.isBlock(innerLeftStatus, innerRightStatus)){
            result.blockCount++;
        }
        else if(this.isWin(innerLeftStatus, innerRightStatus)){
            result.winCount++;
        }
        
        if(innerLeftStatus.isPlayer){
            result.adjacentPlayerCount++;
        }
        
        if(innerRightStatus.isPlayer){
            result.adjacentPlayerCount++;
        }
        
        return result;
    }
    
    private boolean adjacentExists(int row, int column){
        return row >= 0 && row < this.adjMoves.length && column >= 0 && column < this.adjMoves[0].length && this.adjMoves[row][column] != null;
    }
    
    private boolean adjacentIsOpponent(int row, int column){
        if(this.adjacentExists(row, column) && this.adjMoves[row][column].moveTaken()){
            return this.adjMoves[row][column].getMark() != this.getMark();
        }
        else{
            return false;
        }
    }
    
    private boolean adjacentIsPlayer(int row, int column){
        if(this.adjacentExists(row, column)){
            return this.adjMoves[row][column].getMark() == this.getMark();
        }
        else{
            return false;
        }
    }
    
    private boolean isBlock(AdjacentMoveStatus inner, AdjacentMoveStatus outer){
        return inner.isOpponent && outer.isOpponent;
    }
    
    private boolean isWin(AdjacentMoveStatus inner, AdjacentMoveStatus outer){
        return inner.isPlayer && outer.isPlayer;
    }
    
    private AdjacentMoveStatus getMoveStatus(int row, int column){
        return new AdjacentMoveStatus(this.adjacentIsOpponent(row, column), this.adjacentIsPlayer(row, column));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    @Override
    public char getMark(){
        return this.targetMark;
    }
    
    @Override
    public void setMark(char mark){
        this.targetMark = mark;
    }
    
    @Override
    public int getRow(){
        return this.move.getRow();
    }
    
    @Override
    public int getColumn(){
        return this.move.getColumn();
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setAdjacentMove(int row, int column, Move move){
        this.adjMoves[row][column] = move;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Internal Classes">
    private class MoveEvaluation{
        // <editor-fold defaultstate="collapsed" desc="Constructors">
        public MoveEvaluation(){}
        public MoveEvaluation(int blockCount, int winCount, int adjacentPlayerCount){
            this.blockCount = blockCount;
            this.winCount = winCount;
            this.adjacentPlayerCount = adjacentPlayerCount;
        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="fields">
        private int blockCount;
        private int winCount;
        private int adjacentPlayerCount;
        // </editor-fold>
    }
    
    private class RowColumnPair{
        // <editor-fold defaultstate="collapsed" desc="Constructors">
        public RowColumnPair(int row, int column){
            this.row = row;
            this.column = column;
        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Fields">
        private int row;
        private int column;
        // </editor-fold>
    }
    
    private class AdjacentMoveStatus{
        // <editor-fold defaultstate="colapsed" desc="Constructors">
        public AdjacentMoveStatus(boolean isOpponent, boolean isPlayer){
            this.isOpponent = isOpponent;
            this.isPlayer = isPlayer;
        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Fields">
        private boolean isOpponent;
        private boolean isPlayer;
        // </editor-fold>
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private Move move;
    private char targetMark;
    private int weight;
    
    private Move[][] adjMoves;
    // </editor-fold>
}
