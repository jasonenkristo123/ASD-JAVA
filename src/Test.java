import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        String[] nama = new String[n];

        String temp = input.nextLine();

        nama[0] = input.nextLine();

        System.out.println(temp);
    }
}
