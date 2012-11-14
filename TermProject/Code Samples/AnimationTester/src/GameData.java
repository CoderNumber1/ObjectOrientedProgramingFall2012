
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameData {

    final List figures;

    public GameData() {
        figures = Collections.synchronizedList(new ArrayList<GameFigure>());

        figures.add(new Ball(100, 100, 20, Color.blue));
        figures.add(new Ball(150, 150, 30, Color.blue));
        Random r = new Random();
        for (int i = 0; i < 600; i++) {
            int red = r.nextInt(200) + 55;
            int green = r.nextInt(200) + 55;
            int blue = r.nextInt(200) + 55;
            figures.add(new Ball(r.nextInt(GamePanel.PWIDTH),
                    r.nextInt(GamePanel.PHEIGHT), 6, new Color(red, green, blue)));
        }
    }

    public void add(int n) {
        Random r = new Random();
        synchronized (figures) {
            for (int i = 0; i < n; i++) {
                int red = r.nextInt(200) + 55;
                int green = r.nextInt(200) + 55;
                int blue = r.nextInt(200) + 55;
                figures.add(new Ball(r.nextInt(GamePanel.PWIDTH),
                        r.nextInt(GamePanel.PHEIGHT), 6, new Color(red, green, blue)));
            }
        }
    }

    public void update() {
        GameFigure f;
        synchronized (figures) {
            for (int i = 0; i < figures.size(); i++) {
                f = (GameFigure) figures.get(i);
                f.update();
            }
        }
    }
}
