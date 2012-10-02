
import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Baseball baseball = new Baseball();
        char continueInput;
        
        do{
            System.out.println(Util.pad("", '*', 60));
            System.out.printf("*%s*\n", Util.pad("Welcome to Baseball!", ' ', 58, true));
            System.out.printf("*%s*\n", Util.pad("(c) Anthony James", ' ', 58, true));
            System.out.println(Util.pad("", '*', 60));
            
            baseball.StartGame();
            
            System.out.printf("key (for debugging): %s\n", baseball.getKey());
            
            do{
                System.out.print("Guess three numbers: ");
                String guess = scanner.next();
                
                if(baseball.validateGuess(guess)){
                    baseball.guess(guess);
                    System.out.println(baseball.getGameProgressString());
                }
                else{
                    System.out.println("Invalid guess.  Please enter three numbers.");
                }
            }while(!baseball.isGameWon());
            
            System.out.print("Would you like to play again? (y/n): ");
            continueInput = Character.toLowerCase(scanner.next().charAt(0));
            
        }while(continueInput == 'y');
    }
}
