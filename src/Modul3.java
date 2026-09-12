import java.util.Arrays;

public class Modul3 {

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        int[] datas = {30, 87, 90, 3, 1, 50, 23, 4, 25, 23, 40, 35, 47, 2, 33};

        Arrays.sort(datas);
        System.out.println("Array setelah diurutkan: " + Arrays.toString(datas));

        double sum = 0;

        for (int num : datas) {
            sum += num;
        }
        double average = sum / datas.length;
        System.out.println("Rata-rata: " + average);

        int minimal = datas[0];
        int maximal = datas[datas.length - 1];

        System.out.println("Nilai minimal: " + minimal);
        System.out.println("Nilai maksimal: " + maximal);

        System.out.print("Bilangan Ganjil: ");
        for (int num : datas) {
            if (num % 2 != 0) {
                System.out.print(num + " ");
            }
        }

        System.out.println();

        System.out.print("Bilangan Prima: ");
        for (int num : datas) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        System.out.println("5. Array 2 Dimensi (3x5):");
        int[][] data2D = new int[3][5];
        int index = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                data2D[i][j] = datas[index]; 
                System.out.print(data2D[i][j] + "\t");
                index++;
            }
            System.out.println(); 
        }

    }
}
