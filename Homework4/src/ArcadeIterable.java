
import java.util.Iterator;

public interface ArcadeIterable {
    Iterator<GameSelection> getSelectionIterator();
    Iterator<IGame> getGameIterator();
}
