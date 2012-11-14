
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener{
    private GamePanel gamePanel;
    private GameData gameData;
    private Animator animator;
    
    public Main(){
        super.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        Container c = super.getContentPane();
        
        this.animator = new Animator();
        this.gameData = new GameData();
        this.gamePanel = new GamePanel(this.animator, this.gameData);
        
        this.animator.setGamePanel(this.gamePanel);
        this.animator.setGameData(this.gameData);
        
        c.add(this.gamePanel, "Center");
        
        this.gamePanel.setFocusable(true);
        this.gamePanel.addKeyListener(this);
    }
    
    public void startGame(){
        this.gamePanel.startGame();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        switch(Character.toUpperCase(e.getKeyChar())){
            case 'C':
                this.gameData.tank.setFindNearestCell();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.gameData.tank.getState() != GameFigure.STATE_MOVING){
            final int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP:
                    this.gameData.tank.move(GameFigure.DIRECTION_FORWARD);
                    break;
                case KeyEvent.VK_DOWN:
                    this.gameData.tank.move(GameFigure.DIRECTION_BACKWARD);
                    break;
                case KeyEvent.VK_LEFT:
                    this.gameData.tank.move(GameFigure.DIRECTION_LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.gameData.tank.move(GameFigure.DIRECTION_RIGHT);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(this.gameData.tank.getState() == GameFigure.STATE_MOVING){
            final int keyCode = e.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_UP:
                    if(this.gameData.tank.getDirection() == GameFigure.DIRECTION_FORWARD){
                        this.gameData.tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(this.gameData.tank.getDirection() == GameFigure.DIRECTION_BACKWARD){
                        this.gameData.tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(this.gameData.tank.getDirection() == GameFigure.DIRECTION_LEFT){
                        this.gameData.tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(this.gameData.tank.getDirection() == GameFigure.DIRECTION_RIGHT){
                        this.gameData.tank.stopMoving();
                    }
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame game = new Main();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        ((Main)game).startGame();
    }
}
