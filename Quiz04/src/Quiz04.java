import java.util.Scanner;

public class Quiz04 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MyDice myDice = new MyDice();
        int input = 0;
        
        do{
            System.out.print("Enter the number of times to roll the dice: ");
            input = scanner.nextInt();
            System.out.println();
        }while(input < 1000 || input > 100000);
        
        myDice.rollDice(input);
        
        System.out.printf("The dice was rolled %d times.\n", input);
        System.out.printf("  the number of 1s = %d\n", myDice.getOnesCount());
        System.out.printf("  the number of 2s = %d\n", myDice.getTwosCount());
        System.out.printf("  the number of 3s = %d\n", myDice.getThreesCount());
        System.out.printf("  the number of 4s = %d\n", myDice.getFoursCount());
        System.out.printf("  the number of 5s = %d\n", myDice.getFivesCount());
        System.out.printf("  the number of 6s = %d\n", myDice.getSixesCount());
    }
}
