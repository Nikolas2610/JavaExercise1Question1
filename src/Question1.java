import java.util.Arrays;
import java.util.Random;

public class Question1 {
    private static final Random RANDOM = new Random();

    private static final int THREAD_COUNT = 4;
//    Max N = 100000000 in 2070mse with one threat
    private static final int N = 100000000;

    public Question1() {
        long start = System.currentTimeMillis();
        int[] vector1 = generateVector(N);
        int[] vector2 = generateVector(N);
//        printVector(vector1);
//        printVector(vector2);
        long hamming = hamming(vector1, vector2);
        long end = System.currentTimeMillis();
        System.out.println(hamming);

        System.out.println("Start: " + start + "msec");
        System.out.println("End: " + end  + "msec");
        System.out.println("Duration: " + (end - start) + "msec");


    }

    public int[] generateVector(int size) {
        int[] vector = new int[size];

        for (int i = 0; i < size; i++) {
            vector[i] = RANDOM.nextInt(2);
        }
        return vector;
    }

    public void printVector(int[] vector) {
        StringBuilder vectorString = new StringBuilder("[");
        for (int j : vector) {
            vectorString.append(j).append(", ");
        }
        vectorString.deleteCharAt(vectorString.length() - 1);
        vectorString.deleteCharAt(vectorString.length() - 1);
        vectorString.append("]");
        System.out.println(vectorString);
    }

    public boolean hammingOneBit(int[] vector1, int[] vector2, int position) {
        return vector1[position] == vector2[position];
    }

    public long hamming(int[] vector1, int[] vector2) {
        long sumHamming = 0;
        for (int i = 0; i < vector1.length; i++) {
            if (!hammingOneBit(vector1, vector2, i)) {
                sumHamming++;
            }
        }
        return sumHamming;
    }

}
