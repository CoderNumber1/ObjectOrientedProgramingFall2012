
// participant: ConcreteStrategy

public class AggressiveBehavior implements IBehavior {

    @Override
    public int moveCommand() {
        System.out.println("\tAggressive Behavior: if find another robot, ATTACK it");
        return 1;
    }
    
}
