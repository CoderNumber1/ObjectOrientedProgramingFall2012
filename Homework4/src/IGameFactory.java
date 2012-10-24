
import java.lang.reflect.ParameterizedType;

public interface IGameFactory {
    <T extends IGame> IGame createGame(Class<T> gameType);
}
