public class MyCard {
    public MyCard(char suit, int value){
        this.setSuit(suit);
        this.setValue(value);
    }
    
    public String toString(){
        return String.format("%1s%02d", this.getSuit(), this.getValue());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private char suit;
    private int value;
    // </editor-fold>
}
