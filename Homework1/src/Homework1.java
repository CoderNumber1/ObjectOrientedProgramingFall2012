
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Hangman hangman = new Hangman();
        char input;
        
        do{
            System.out.println(Util.pad("", '*', 60));
            System.out.printf("*%s*\n", Util.pad("Welcome to Hangman!", ' ', 58, true));
            System.out.printf("*%s*\n", Util.pad("(c) Anthony James", ' ', 58, true));
            System.out.println(Util.pad("", '*', 60));
        
            hangman.startGame();

            System.out.printf("key (for debugging): %s\n", hangman.getKey());

            do{
                System.out.printf("%s UnusedLetters: %s\n", Util.pad("Guesss this word: " + hangman.getGameProgress(), ' ', 38), hangman.getUnusedLetters());
                System.out.printf("[%d] Guess a letter: ", (hangman.getGuessCount() + 1));
                hangman.guess(scanner.next().charAt(0));
            }while(!hangman.getGameWon());

            System.out.printf("\n%s: You got it in %d guesses.\n\n", hangman.getKey(), hangman.getGuessCount());
            
            System.out.print("Would you like to play again? (y/n): ");
            
            input = Character.toLowerCase(scanner.next().charAt(0));
        }while(input == 'y');
    }
}
