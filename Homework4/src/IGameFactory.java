public interface IGameFactory<T extends IGame> {
    public IGame CreateGame();
}
