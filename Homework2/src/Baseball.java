public class Baseball {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commands">
    public void StartGame(){
        int[] keys = new int[3];
        
        keys[0] = (int)(Math.random() * (10));
        
        do{
            keys[1] = (int)(Math.random() * (10));
        } while(keys[1] == keys[0]);
        
        do{
            keys[1] = (int)(Math.random() * (10));
        } while(keys[2] == keys[0] || keys[2] == keys[1]);
        
        this.key = String.format("%d%d%d", keys[0], keys[1], keys[2]);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String key;
    // </editor-fold>
}
