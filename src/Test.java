import java.util.Scanner;

public class Test {

    public void add(String nama, int index, String[] outputBuffer, int outputIndex) {
        System.out.println("helolo");
    }
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 10, 20, 2, 50, 100, 2};

        int minValue = data[0];
        int maxValue = data[0];

        for (int i = 1; i < data.length; i++) {
            if (data[i] < minValue) {
                minValue = data[i];
            }
            if (data[i] > maxValue) {
                maxValue = data[i];
            }
        }

        System.out.println("Nilai minimal: " + minValue);
        System.out.println("Nilai maksimal: " + maxValue);


    }
}


class Clients {
    private String[] client;
    private static final int maxIndex = 7;

    public Clients() {
        this.client = new String[maxIndex];
        for (int i = 0; i < maxIndex; i++) {
            this.client[i] = "-";
        }
    }
}
