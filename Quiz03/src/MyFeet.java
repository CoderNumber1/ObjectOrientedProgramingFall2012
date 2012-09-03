public class MyFeet {
    private double length;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public double getLengthInMeters()
    {
        double result = this.length * 0.3048;
        
        return result;
    }
}
