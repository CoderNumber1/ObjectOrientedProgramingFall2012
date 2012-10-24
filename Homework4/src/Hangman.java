
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@GameMetadata(displayName = "Hangman")
public class Hangman implements IGame {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Hangman(){
        this.gameProgress = new StringBuffer();
        this.unusedLetters = new StringBuffer();
        
        this.keys = new ArrayList<>();
        this.keys.add("train");
        this.keys.add("jungle");
        this.keys.add("city");
        this.keys.add("whale");
        this.keys.add("antidisestablishmentarianism");
        this.keys.add("airplane");
        this.keys.add("mustang");
        this.keys.add("einstein");
        this.keys.add("gestalt");
        this.keys.add("visual");
        this.keys.add("studio");
        this.keys.add("database");
        this.keys.add("server");
        this.keys.add("windows");
        this.keys.add("winning");
        this.keys.add("tiger");
        this.keys.add("mobile");
        this.keys.add("phone");
        this.keys.add("coffee");
        this.keys.add("java");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Commands">
    @Override
    public void move(String guess) {
        char guessChar = Character.toLowerCase(guess.charAt(0));
        
        for(int i = 0; i < this.key.length(); i ++){
            if(this.key.charAt(i) == guessChar){
                this.gameProgress.replace(i, i + 1, String.valueOf(guessChar));
            }
        }
        
        int unusedIndex = this.unusedLetters.indexOf(String.valueOf(guessChar));
        if(unusedIndex > 0){
            this.unusedLetters.replace(unusedIndex, unusedIndex + 1, "*");
        }
        this.gameWon = this.gameProgress.toString().equals(this.key);
        this.guessCount++;
    }
    
    @Override
    public void startGame(){
        //reset game fields
        this.gameWon = false;
        this.guessCount = 0;
        this.gameProgress.setLength(0);
        this.unusedLetters.setLength(0);
        this.key = "";
        
        int keyIndex = (int)(Math.random() * (this.keys.size() + 1));
        
        //initialize game
        this.key = this.keys.get(keyIndex);
        this.gameProgress.append(Util.pad("", '_', this.key.length()));
        this.unusedLetters.append("abcdefghijklmnopqrstuvwxyz");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    @Override
    public boolean validateInput(String guess) {
        return guess.length() == 1 && "ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(guess.toUpperCase());
    }

    @Override
    public String getGameStatus() {
        return String.format("%s UnusedLetters: %s", String.format("Guesss this word: %s (key: %s)", this.gameProgress, this.key), this.unusedLetters);
    }

    @Override
    public boolean isGameWon() {
        return this.gameWon;
    }

    @Override
    public String getGameDisplayName() {
        return "Hangman";
    }

    @Override
    public String getGameInstruction() {
        return String.format("[%d] Guess a letter: ", (this.guessCount + 1));
    }
    
    @Override
    public int getScore() {
        ArrayList<Character> characterList = new ArrayList<>();
        char[] keyChars = this.key.toCharArray();
        
        for(char k : keyChars){
            if(!characterList.contains((Character)k)){
                characterList.add((Character)k);
            }
        }
        
        return 100 - (this.guessCount > characterList.size() ? (this.guessCount - characterList.size()) * 5 : 0);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
//    public ArrayList<String> getKeys() {
//        return keys;
//    }
//
//    public void setKeys(ArrayList<String> keys) {
//        this.keys = keys;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//
//    public StringBuffer getGameProgress() {
//        return gameProgress;
//    }
//
//    public void setGameProgress(StringBuffer gameProgress) {
//        this.gameProgress = gameProgress;
//    }
//
//    public int getGuessCount() {
//        return guessCount;
//    }
//
//    public void setGuessCount(int guessCount) {
//        this.guessCount = guessCount;
//    }
//    
//    public boolean getGameWon(){
//        return this.gameWon;
//    }
//    
//    public void setUnusedLetters(StringBuffer unusedLetters){
//        this.unusedLetters = unusedLetters;
//    }
//    
//    public StringBuffer getUnusedLetters(){
//        return this.unusedLetters;
//    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private ArrayList<String> keys;
    private String key;
    private StringBuffer gameProgress;
    private StringBuffer unusedLetters;
    private int guessCount;
    private boolean gameWon;
    // </editor-fold>
}
