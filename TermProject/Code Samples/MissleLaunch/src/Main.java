
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame
        implements ActionListener, MouseListener {

    private GamePanel gamePanel;
    private GameData gameData;
    private Animator animator;
    private JButton startButton;
    private JButton quitButton;

    public Main() {
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
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        gamePanel.addMouseListener(this);
        startButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startButton) {
            gamePanel.startGame();
        } else if (ae.getSource() == quitButton) {
            animator.running = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        Color color;
        double r = Math.random();
        if (r < 0.25) color = Color.red;
        else if (r < 0.5) color = Color.blue;
        else if (r < 0.75) color = Color.gray;
        else color = Color.green;

        Missile f = new Missile(GamePanel.PWIDTH/2, GamePanel.PHEIGHT-50, color);
        f.setTarget(x, y);
        int size = (int) (Math.random() * 100) + 5; // min = 5 max = 105
        f.setExplosionMaxSize(size);

        gameData.figures.add(f);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public static void main(String[] args) {
        JFrame game = new Main();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
