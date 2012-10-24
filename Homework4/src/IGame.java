public interface IGame {
    void startGame();
    void move(String guess);
    boolean validateInput(String guess);
    String getGameStatus();
    String getGameInstruction();
    boolean isGameWon();
    String getGameDisplayName();
    int getScore();
}
