public class MyMeters {
    private double length;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public double getLengthInFeet()
    {
        double result = this.length * 3.28084;
        
        return result;
    }
}
