
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameFigure {

    // Note: package visibility for faster access
    int radius;
    Color color;
    private int dx = 3;
    private int dy = 3;

    public Ball(int x, int y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawOval(x-radius, y-radius, radius*2, radius*2);
    }

    @Override
    public void update() {
        // ball bounces on the wall
        super.x += dx;
        super.y += dy;
        
        if (super.x + radius > GamePanel.PWIDTH) {
            dx = -dx;
            super.x = GamePanel.PWIDTH - radius;
        } else if (super.x - radius < 0) {
            dx = -dx;
            super.x = radius;
        }

        if (super.y + radius > GamePanel.PHEIGHT) {
            dy = -dy;
            super.y = GamePanel.PHEIGHT - radius;
        } else if (super.y - radius < 0) {
            dy = -dy;
            super.y = radius;
        }
    }
}
