
public class Demo_printf {

    public static void main(String[] args) {


        int i = 12345;
        System.out.println("123456789012345678901234567890");

        System.out.printf("|%d|%8d|%2d|\n", i, i, i);

        double d = 12.34567;
        System.out.println("123456789012345678901234567890");

        System.out.printf("|%.4f|%8.2f|\n", d, d);

        String s = "hello";
        System.out.println("123456789012345678901234567890");

        System.out.printf("|%10s|%-10s|\n", s, s);
    }
}
