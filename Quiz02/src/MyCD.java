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
    
    public double GetCDValue(){
        double currentValue = this.principal;
        
        for(int i = 1; i <= this.numberOfMonths; i ++){
            currentValue += (currentValue * this.annualRate / 1200);
        }
        
        return currentValue;
    }
}
