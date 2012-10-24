
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Homework4 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Arcade arcade = new Arcade();
        int menuSelection = -1;
        
        do{
            menuSelection = displayMenu(arcade);
            
            if(menuSelection > 0){
                arcade.selectGame(menuSelection - 1);
                arcade.startGame();

                do{
                    System.out.println(arcade.getGameStatus());

                    String input;
                    do{
                        System.out.print(arcade.getGameInstruction());
                        input = scanner.nextLine();
                    }while(!arcade.validateInput(input));

                    arcade.move(input);
                }while(!arcade.isGameWon());
            }
        }while(menuSelection != 0);
        
        System.out.println("----------Game History----------");
        
        int gamesCount = 0;
        Iterator<IGame> gamesIterator = arcade.getGameIterator();
        while(gamesIterator.hasNext()){
            gamesCount++;
            IGame game = gamesIterator.next();
            System.out.printf("%d: %s %d\n", gamesCount, game.getGameDisplayName(), game.getScore());
        }
        
        System.out.println("----------Game Stats----------");
        
        Map<String, ArrayList<Integer>> scores = new HashMap<String, ArrayList<Integer>>();
        Iterator<IGame> statsIterator = arcade.getGameIterator();
        while(statsIterator.hasNext()){
            IGame game = statsIterator.next();
            if(!scores.containsKey(game.getGameDisplayName())){
                scores.put(game.getGameDisplayName(), new ArrayList<Integer>());
            }
            
            scores.get(game.getGameDisplayName()).add(game.getScore());
        }
        
        for(String key : scores.keySet()){
            int total = 0;
            
            for(Integer score : scores.get(key)){
                total += score;
            }
            
            System.out.printf("%s %d\n", key, total / scores.get(key).size());
        }
    }
    
    public static int displayMenu(Arcade arcade){
        int result;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        Iterator<GameSelection> selectionIterator = arcade.getSelectionIterator();
        
        while(selectionIterator.hasNext()){
            GameSelection selection = selectionIterator.next();
            
            System.out.printf("%d: %s\n", selection.getIndex() + 1, selection.getName());
            count++;
        }
        
        System.out.println("0: Quit");
        
        do{
            System.out.print("Select a game from the menu: ");
            result = scanner.nextInt();
        }while(result < 0 || result > count);
        
        return result;
    }
}
