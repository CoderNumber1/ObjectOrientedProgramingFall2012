import java.util.Scanner;

public class Quiz01 {
    public static void main(String[] args){
        int input;
        Scanner scanner = new Scanner(System.in);
        MyNumbers myNumbers = new MyNumbers();
        
        do{
            System.out.println("Enter a number between 1 and 10");
            input = scanner.nextInt();
        } while(input < 1 || input > 10);
        
        for(int i = 1; i <= input; i ++){
            myNumbers.setNumber(i);
            System.out.println(myNumbers.getLine());
        }
        
        for(int i = input - 1; i > 0; i--){
            myNumbers.setNumber(i);
            System.out.println(myNumbers.getLine());
        }
    }
}
