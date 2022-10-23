import java.util.Arrays;
import java.util.Random;

public class Question1 {
    private static final Random RANDOM = new Random();
    private static final int THREAD_COUNT = 8;
    private static final int N = 536870912;
//private static final int N = 32;
    public Question1() {
        long start = System.currentTimeMillis();
        int[] vector1 = generateVector(N);
        int[] vector2 = generateVector(N);
//        printVector(vector1);
//        printVector(vector2);
        int batchSize = N / THREAD_COUNT;

        ProcessThread[] threads = new ProcessThread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            System.out.println(i);
            threads[i] = new ProcessThread(vector1, vector2, i*batchSize, batchSize);
            threads[i].start();
        }

        for (ProcessThread thread : threads) {
            try {
                // Wait for the threads to finish.
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
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
}
