
// demonstrating Strategy Design pattern
// source: http://www.oodesign.com    Strategy Pattern

public class Main {

	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehavior(new AggressiveBehavior());
		r2.setBehavior(new DefensiveBehavior());
		r3.setBehavior(new NormalBehavior());

		r1.move();
		r2.move();
		r3.move();

		System.out.println("\r\nNew behaviours: " +
				"\r\n\t'Big Robot' gets really scared" +
				"\r\n\t, 'George v.2.1' becomes really mad because" +
				"it's always attacked by other robots" +
				"\r\n\t and R2 keeps its calm\r\n");

		r1.setBehavior(new DefensiveBehavior());
		r2.setBehavior(new AggressiveBehavior());

		r1.move();
		r2.move();
		r3.move();
	}
}