
// participant: ConcreteStrategy

public class DefensiveBehavior implements IBehavior {

    @Override
    public int moveCommand() {
        System.out.println("\tDefensive Behavior: if find another robot, RUN from it");
        return -1;
    }
    
}
