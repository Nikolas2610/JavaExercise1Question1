import java.util.Random;

public class Question1 {
    private static final Random RANDOM = new Random();
    private static final int N = 1073741824;

    private long hamming = 0;
//  Get Hamming value
    public long getHamming() {
        return hamming;
    }

//    Set the hamming value
    public void setHamming(long hamming) {
        this.hamming = hamming;
    }

    public Question1(int THREAD_COUNT) {
        System.out.println("============" + THREAD_COUNT + " THREAD============");
//       Generate 2 arrays vector
        int[] vector1 = generateVector(N);
        int[] vector2 = generateVector(N);
//      Divide the array with threads

        int batchSize = N / THREAD_COUNT;

//      Create threads array
        ProcessThread[] threads = new ProcessThread[THREAD_COUNT];
//      Get msec before program start calculate the hamming
        long start = System.currentTimeMillis();

//      Start Threads to calculate the hamming
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ProcessThread(vector1, vector2, i * batchSize, batchSize);
            threads[i].start();
        }

//      Wait the threads to finish
        for (ProcessThread thread : threads) {
            try {
                // Wait for the threads to finish.
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //   Calculate the sum hamming
        for (ProcessThread thread : threads) {
            setHamming(getHamming() + thread.getHamming());
        }

//      Print the end time to calculation the process time
        long end = System.currentTimeMillis();
        System.out.println("Hamming is " + getHamming());
        System.out.println("Duration for " + THREAD_COUNT + " threads: " + (end - start) + "msec");
        System.out.println("============END " + THREAD_COUNT + " THREAD============");
    }

    //  Generate vectors function
    public int[] generateVector(int size) {
        int[] vector = new int[size];

        for (int i = 0; i < size; i++) {
            vector[i] = RANDOM.nextInt(2);
        }
        return vector;
    }

    //  Print Vector arrays for debug
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
