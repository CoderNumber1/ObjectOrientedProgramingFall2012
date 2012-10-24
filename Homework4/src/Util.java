public class Util {
    public static String pad(String input, char padding, int size){
        return pad(input, padding, size, false);
    }
    
    public static String pad(String input, char padding, int size, boolean split){
        StringBuffer result = new StringBuffer();
        int paddingSize = Math.abs(input.length() - Math.abs(size));
        
        for(int i = 1; i <= paddingSize; i ++){
            result.append(padding);
        }
        
        if(split){
            result.insert((int)size/2 - (int)input.length()/2 - 1, input);
        }
        else if(size >= 0){
            result.insert(0, input);
        }
        else{
            result.append(input);
        }
        
        return result.toString();
    }
}
