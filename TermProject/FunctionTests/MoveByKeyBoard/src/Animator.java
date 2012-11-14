public class Animator implements Runnable {
    boolean running;
    GamePanel gamePanel = null;
    GameData gameData = null;
    
    public Animator(){}

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    public void setGameData(GameData gameData){
        this.gameData = gameData;
    }
    
    @Override
    public void run() {
        this.running = true;
        while (this.running) {
            this.gameData.update();
            this.gamePanel.gameRender();
            this.gamePanel.printScreen();
            try {
                Thread.sleep(15); //Cranking this down to 0 for the demo.
            } catch (InterruptedException e) {
                
            }
        }
        System.exit(0);
    }
    
}
