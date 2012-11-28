
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener, FieldObserver{
    private GamePanel gamePanel;
    private Animator animator;
    
    public Main(){
        Container c = super.getContentPane();
        
        this.animator = new Animator();
        this.gamePanel = new GamePanel(this.animator);
        
        this.animator.setGamePanel(this.gamePanel);
        
        c.add(this.gamePanel, "Center");
        
        this.gamePanel.setFocusable(true);
        this.gamePanel.addKeyListener(this);
        
        GameData.getInstance().watchForFieldChange(this);
        GameData.getInstance().watchForFieldChange(WeaponManager.getInstance());
        GameData.getInstance().watchForFieldChange(GameResources.getInstance());
    }
    
    public void startGame(){
        this.gamePanel.startGame();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        switch(Character.toUpperCase(e.getKeyChar())){
            case 'C':
                GameData.getInstance().tank.setFindNearestCell();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int keyCode = e.getKeyCode();
        
        if(GameData.getInstance().tank.getState() != GameFigure.STATE_MOVING){
            
            switch (keyCode){
                case KeyEvent.VK_UP:
                    GameData.getInstance().tank.move(GameFigure.DIRECTION_FORWARD);
                    break;
                case KeyEvent.VK_DOWN:
                    GameData.getInstance().tank.move(GameFigure.DIRECTION_BACKWARD);
                    break;
                case KeyEvent.VK_LEFT:
                    GameData.getInstance().tank.move(GameFigure.DIRECTION_LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    GameData.getInstance().tank.move(GameFigure.DIRECTION_RIGHT);
                    break;
            }
        }
        
        if(keyCode == KeyEvent.VK_SPACE){
            GameData.getInstance().tank.fireWeapon();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(GameData.getInstance().tank.getState() == GameFigure.STATE_MOVING){
            final int keyCode = e.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_UP:
                    if(GameData.getInstance().tank.getDirection() == GameFigure.DIRECTION_FORWARD){
                        GameData.getInstance().tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(GameData.getInstance().tank.getDirection() == GameFigure.DIRECTION_BACKWARD){
                        GameData.getInstance().tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(GameData.getInstance().tank.getDirection() == GameFigure.DIRECTION_LEFT){
                        GameData.getInstance().tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(GameData.getInstance().tank.getDirection() == GameFigure.DIRECTION_RIGHT){
                        GameData.getInstance().tank.stopMoving();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    GameData.getInstance().tank.ceaseFire();
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

    @Override
    public void fieldLoaded(Field field) {
        super.getContentPane().setSize(field.getPixelWidth(), field.getPixelHeight());
        super.setSize(field.getPixelWidth() + 15, field.getPixelHeight() + 40);
    }
}
