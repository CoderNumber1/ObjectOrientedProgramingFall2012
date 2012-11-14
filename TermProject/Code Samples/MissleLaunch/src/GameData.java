
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameData {

    final List figures;

    public GameData() {
        figures = Collections.synchronizedList(new ArrayList<GameFigure>());
        
        figures.add(new Launcher(GamePanel.PWIDTH/2-30, GamePanel.PHEIGHT-60));
    }

    public void update() {
        List remove = new ArrayList<GameFigure>();
        GameFigure f;
        synchronized (figures) {
            for (int i = 0; i < figures.size(); i++) {
                f = (GameFigure) figures.get(i);
                f.update();
                if (f.getState() == GameFigure.STATE_DONE) {
                    remove.add(f);
                }
            }
            figures.removeAll(remove);
        }
    }
}
