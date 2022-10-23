public class ProcessThread extends Thread{
    private final int[] vector1;
    private final int[] vector2;
    private final int start;
    private final int batchSize;
    private long hamming;

    public ProcessThread(int[] vector1, int[] vector2, int start, int batchSize) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.start = start;
        this.batchSize = batchSize;
    }

    public long getHamming() {
        return hamming;
    }

    public void setHamming(long hamming) {
        this.hamming = hamming;
    }

    @Override
    public void run() {
        setHamming(this.hamming = hamming(vector1, vector2, batchSize, start));
        System.out.println("-----------------------");
        System.out.println("Hamming " + getName() + ": " + getHamming());
        System.out.println(getName() + " finished.");
    }

    public long hamming(int[] vector1, int[] vector2, int batchSize, int start) {
        long sumHamming = 0;
        System.out.println(getName() + " start: " + start + " until: " + (start + batchSize));
        for (int i = start; i < start + batchSize; i++) {
            if (vector1[i] != vector2[i]) {
                sumHamming++;
            }
//            System.out.println(getName() + " ham: " + sumHamming);
//            try {
//                Thread.sleep(1);
//            } catch (Exception ignored) {
//
//            }
        }
        return sumHamming;
    }
}
