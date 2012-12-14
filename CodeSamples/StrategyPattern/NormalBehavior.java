
// participant: ConcreteStrategy

public class NormalBehavior implements IBehavior {

    @Override
    public int moveCommand() {
        System.out.println("\tNormal Behavior: if find another robot, IGNORE from it");
        return 0;
    }
    
}
