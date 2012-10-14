public interface Move {
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public boolean moveTaken();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getRow();
    
    public int getColumn();
    
    public char getMark();
    
    public void setMark(char mark);
    // </editor-fold>
}
