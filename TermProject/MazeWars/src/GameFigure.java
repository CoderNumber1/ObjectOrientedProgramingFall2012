
import java.awt.Graphics;

public interface GameFigure {
    public void render(Graphics g);
    public void update();
    public int getState();
    public int getDirection();
    
    static final int STATE_MOVING = 1;
    static final int STATE_STATIONARY = 2;
    static final int STATE_EXPLODING = 3;
    
    static final int DIRECTION_LEFT = 0;
    static final int DIRECTION_RIGHT = 1;
    static final int DIRECTION_FORWARD = 2;
    static final int DIRECTION_BACKWARD = 3;
}
