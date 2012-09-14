
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Hangman hangman = new Hangman();
        
        hangman.startGame();
        
        System.out.printf("key: %s\n", hangman.getKey());
        System.out.println("enter a guess");
        
        while(!hangman.getGameWon()){
            hangman.guess(scanner.next().toCharArray()[0]);
            
            System.out.println(hangman.getGameProgress().toString() + " " + hangman.getGameWon() + " " + hangman.getKey());
        }
        
        System.out.printf("Game won in %d guesses", hangman.getGuessCount());
    }
}
