public class GameFactory implements IGameFactory {
    public GameFactory(){ }
    
    @Override
    public <T extends IGame> IGame createGame(Class<T> gameType){
        try {
            return (T)gameType.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }
}
