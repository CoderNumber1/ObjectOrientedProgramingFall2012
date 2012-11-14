
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    public static final int PWIDTH = 600; // size of the game panel
    public static final int PHEIGHT = 400;
        
    private Animator animator;
    private GameData gameData;
    // off screen rendering
    private Graphics graphics;
    private Image dbImage = null;

    public GamePanel(Animator animator, GameData gameData) {
        this.animator = animator;
        this.gameData = gameData;
        setBackground(Color.blue);
        setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
    }

    public void startGame() {
        Thread t = new Thread(animator);
        t.start();
    }

    public void gameRender() {
        if (dbImage == null) {
            dbImage = createImage(PWIDTH, PHEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                graphics = dbImage.getGraphics();
            }
        }

        graphics.clearRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);

        synchronized (gameData.figures) {
            GameFigure f;
            for (int i = 0; i < gameData.figures.size(); i++) {
                f = (GameFigure) gameData.figures.get(i);
                f.render(graphics);
            }
        }

    }

    public void printScreen() { // use active rendering to put the buffered image on-screen
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
}
