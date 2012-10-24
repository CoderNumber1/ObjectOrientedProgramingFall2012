
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class Arcade implements ArcadeIterable, IGame {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Arcade(){
        this.factory = new GameFactory();
        this.playedGames = new ArrayList<>();
        this.registeredGames = new ArrayList<>();
        
        this.registeredGames.add((Class<IGame>)Hangman.class.asSubclass(IGame.class));
        this.registeredGames.add((Class<IGame>)Baseball.class.asSubclass(IGame.class));
        this.registeredGames.add((Class<IGame>)TicTacToe.class.asSubclass(IGame.class));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Queries">
    @Override
    public Iterator<GameSelection> getSelectionIterator(){
        return new ArcadeIterator(this.registeredGames);
    }
    
    @Override
    public Iterator<IGame> getGameIterator(){
        return this.playedGames.iterator();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="IGame">
    @Override
    public void startGame() {
        this.currentGame.startGame();
    }

    @Override
    public void move(String guess) {
        this.currentGame.move(guess);
    }

    @Override
    public boolean validateInput(String guess) {
        return this.currentGame.validateInput(guess);
    }

    @Override
    public String getGameStatus() {
        return this.currentGame.getGameStatus();
    }

    @Override
    public boolean isGameWon() {
        return this.currentGame.isGameWon();
    }
    
    @Override
    public String getGameDisplayName(){
        return "Arcade";
    }
    
    @Override
    public String getGameInstruction() {
        return this.currentGame.getGameInstruction();
    }
    
    @Override
    public int getScore() {
        throw new UnsupportedOperationException();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Commands">
    public void selectGame(int selectedGame){
        this.currentGame = this.factory.createGame(this.registeredGames.get(selectedGame));
        
        this.playedGames.add(this.currentGame);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private ArrayList<Class<IGame>> registeredGames;
    private ArrayList<IGame> playedGames;
    private IGame currentGame;
    private IGameFactory factory;
    // </editor-fold>
}
