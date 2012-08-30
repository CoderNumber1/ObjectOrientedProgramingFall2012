/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony
 */
public class MyCD {
    private double principal;
    private double annualRate;
    private int numberOfMonths;
    
    public MyCD(double principal, double annualRate, int numberOfMonths){
        this.principal = principal;
        this.annualRate = annualRate;
        this.numberOfMonths = numberOfMonths;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(double annualRate) {
        this.annualRate = annualRate;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }
    
    public double GetCDValue(int month){
        double currentValue = this.principal;
        
        for(int i = 1; i <= month; i ++){
            currentValue += (currentValue * this.annualRate / 1200);
        }
        
        return currentValue;
    }
}
