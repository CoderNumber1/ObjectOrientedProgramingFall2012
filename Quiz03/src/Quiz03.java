public class Quiz03 {
    public static void main(String[] args)
    {
        MyFeet myFeet = new MyFeet();
        MyMeters myMeters = new MyMeters();
        
        System.out.printf("%-14s%-14s%-14s%-14s\n\n", "Feet", "Meters", "Meters", "Feet");
        
        int metersCount = 20;
        for(int i = 1; i <= 10; i ++)
        {
            myFeet.setLength(i);
            myMeters.setLength(metersCount);
            
            System.out.printf("%-14.1f%-14.3f%-14.1f%-14.3f\n", myFeet.getLength(), myFeet.getLengthInMeters(), myMeters.getLength(), myMeters.getLengthInFeet());
            
            metersCount += 5;
        }
    }
}
