public class MyNumbers {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getLine(){
        String result = "";
        
        for(int i = 1; i <= this.number; i++){
            result += i;
        }
        
        return result;
    }
}
