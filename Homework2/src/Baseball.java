public class Baseball {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commands">
    public void StartGame(){
        int[] keys = new int[3];
        
        keys[0] = (int)(Math.random() * (10));
        
        do{
            keys[1] = (int)(Math.random() * (10));
        } while(keys[1] == keys[0]);
        
        do{
            keys[1] = (int)(Math.random() * (10));
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String key;
    private int balls;
    private int strikes;
    private boolean gameWon;
    private int guessCount;
    // </editor-fold>
}
