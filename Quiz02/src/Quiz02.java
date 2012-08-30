import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args){
        double principalInput;
        double annualRateInput;
        int numberOfMonthsInput;
        Scanner scanner = new Scanner(System.in);
        MyCD myCD = new MyCD();
        
        do{
            System.out.print("Enter the initial deposit amount: ");
            principalInput = scanner.nextDouble();
            System.out.println();
        } while(principalInput <= 0);
        myCD.setPrincipal(principalInput);
        
        do{
            System.out.print("Enter the annual percentege yield: ");
            annualRateInput = scanner.nextDouble();
            System.out.println();
        } while(annualRateInput <= 0);
        myCD.setAnnualRate(annualRateInput);
        
        do{
            System.out.print("Enter maturity period (number of months): ");
            numberOfMonthsInput = scanner.nextInt();
            System.out.println();
        } while(numberOfMonthsInput <= 0);
        
        System.out.printf("%-15s%8s\n", "Month", "CD Value");
        for(int i = 1; i <= numberOfMonthsInput; i ++){
            myCD.setNumberOfMonths(i);
            System.out.printf("%2d%13s%8.2f\n", i, "", myCD.GetCDValue());
        }
    }
}
