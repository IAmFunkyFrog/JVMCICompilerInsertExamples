public class Main
{
    public static int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        int lastSum = 0;

        for(int i = 0; i < 5000; i++) {
            System.out.println("Using add method to sum " + lastSum + " with " + i);
            lastSum = add(i, lastSum);
        }
        System.out.println("Sum from 0 to 4999: " + lastSum);
    }
}
