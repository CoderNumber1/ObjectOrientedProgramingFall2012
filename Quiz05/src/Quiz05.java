
import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args){
        MyDeck myDeck = new MyDeck();
        boolean exit = false;
        
        do{
            int input = - 1;
            
            input = retrieveMenuChoice();
            
            while(input < 1 || input > 3){
                System.out.println("Invalid menu option.\n");
                
                input = retrieveMenuChoice();
            }
            
            switch(input){
                case 1:
                    MyCard[] cards = myDeck.getMyCards();
                    for(int i = 0; i < cards.length; i ++){
                        System.out.print(cards[i].toString() + ' ');
                    }
                    
                    System.out.print("\n\n");
                    break;
                case 2:
                    myDeck.shuffleDeck();
                    System.out.println();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }while(!exit);
    }
    
    public static int retrieveMenuChoice(){
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        
        System.out.println("1.  Display the deck");
        System.out.println("2.  Shuffle the cards");
        System.out.println("3.  Quit");
        
        System.out.print("(Choose a menu 1, 2, or 3): ");
        input = scanner.nextInt();
        
        return input;
    }
}
