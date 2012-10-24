public class GameSelection{
    public GameSelection(int index, String name){
        this.gameIndex = index;
        this.gameName = name;
    }

    public int getIndex(){
        return this.gameIndex;
    }

    public String getName(){
        return this.gameName;
    }

    private int gameIndex;
    private String gameName;
}