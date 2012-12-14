
// participant: Context

public class Robot {
    
    private IBehavior behavior;
    private String name;
    
    public Robot(String name) {
        this.name = name;
    }
    
    public void setBehavior(IBehavior behavior) {
        this.behavior = behavior;
    }
    
    public IBehavior getBehavior() {
        return behavior;
    }
    
    public void move() {
        System.out.println(this.name + ": Based on current position" +
                "the behavior object decide the next move:");
        int command = behavior.moveCommand();
        
        //  ... send the command to the mechanisms
        System.out.println("\tThe result returned by behavior object " +
                "is sent to the movement mechanisms " +
                " for the robot '" + this.name + "'");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
