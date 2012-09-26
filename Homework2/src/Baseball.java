public class Baseball {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Baseball(){}
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commands">
    public void StartGame(){
        this.guessCount = 0;
        this.balls = 0;
        this.strikes = 0;
        this.gameWon = false;
        
        int[] keys = new int[3];
        
        keys[0] = (int)(Math.random() * (10));
        
        do{
            keys[1] = (int)(Math.random() * (10));
        } while(keys[1] == keys[0]);
        
        do{
            keys[2] = (int)(Math.random() * (10));
        } while(keys[2] == keys[0] || keys[2] == keys[1]);
        
        this.key = String.format("%d%d%d", keys[0], keys[1], keys[2]);
    }
    
    public void guess(String guess){
        this.balls = 0;
        this.strikes = 0;
        
        if(this.key.equals(guess)){
            //No need to keep checking if they got it right.
            this.strikes = 3;
        }
        else{
            char[] guessChars = guess.toCharArray();
            
            /*Loop through each character in the guess
             *  to see if it is in the key and if it is a ball or a strike.
             */
            for(int i = 0; i < guessChars.length; i ++){
                char guessChar = guessChars[i];
                int keyIndex = this.key.indexOf(guessChar);
                
                if(keyIndex > -1){
                    if(keyIndex == i){
                        this.strikes++;
                    }
                    else{
                        this.balls++;
                    }
                }
            }
        }
        
        this.gameWon = this.strikes == 3;
        this.guessCount++;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public boolean validateGuess(String guess){
        if(guess.length() != 3){
            return false;
        }
        
        try{
            Integer.parseInt(guess);
        }
        catch(NumberFormatException ex){
            return false;
        }
        
        char[] guessChars = guess.toCharArray();
        
        if(guessChars[1] == guessChars[0]){
            return false;
        }
        
        if(guessChars[2] == guessChars[1] || guessChars[2] == guessChars[0]){
            return false;
        }
        
        return true;
    }
    
    public String getGameProgressString(){
        if(this.gameWon){
            return String.format("Congratulations!  You are correct!  It took you %d tries.", this.guessCount);
        }
        else{
            return String.format("Strike(s): %d Ball(s): %d", this.strikes, this.balls);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String key;
    private int balls;
    private int strikes;
    private boolean gameWon;
    private int guessCount;
    // </editor-fold>
}
