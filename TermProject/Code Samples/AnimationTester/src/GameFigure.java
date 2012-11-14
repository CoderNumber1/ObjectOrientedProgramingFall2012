
import java.awt.Graphics;

public abstract class GameFigure {
    
    int x; // package visibility for quick access
    int y;
    
    public GameFigure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
    public abstract void update();
}
