
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimationTester extends JFrame
        implements ActionListener, MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private GameData gameData;
    private Animator animator;
    private int px; // mouse pressed point
    private int py;
    private JButton startButton;
    private JButton addButton;
    private JButton quitButton;

    public AnimationTester() {
        setSize(615, 480);
        Container c = getContentPane();
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel(animator, gameData);
        animator.setGamePanel(gamePanel);
        animator.setGameData(gameData);
        c.add(gamePanel, "Center");

        JPanel southPanel = new JPanel();
        startButton = new JButton("Start");
        southPanel.add(startButton);
        addButton = new JButton("Add 100");
        southPanel.add(addButton);
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
        startButton.addActionListener(this);
        addButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startButton) {
            gamePanel.startGame();
        } else if (ae.getSource() == addButton) {
            gameData.add(100);
        } else if (ae.getSource() == quitButton) {
            animator.running = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        px = me.getX();
        py = me.getY();
        gamePanel.areaSelector = new Rectangle();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        List remove = new ArrayList<GameFigure>();
        synchronized (gameData.figures) {
            for (int i = 0; i < gameData.figures.size(); i++) {
                GameFigure f = (GameFigure) gameData.figures.get(i);
                if (gamePanel.areaSelector.contains(f.x, f.y)) {
                    remove.add(f);
                }
            }
            gameData.figures.removeAll(remove);
        }
        gamePanel.areaSelector = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        int dx = me.getX(); // drag point
        int dy = me.getY();
        int x, y, w, h;
        if (px < dx) {
            x = px;
            w = dx - px;
        } else {
            x = dx;
            w = px - dx;
        }
        if (py < dy) {
            y = py;
            h = dy - py;
        } else {
            y = dy;
            h = py - dy;
        }
        gamePanel.areaSelector.setRect(x, y, w, h);
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    public static void main(String[] args) {
        JFrame game = new AnimationTester();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
