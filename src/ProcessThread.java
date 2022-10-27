public class ProcessThread extends Thread{
    private final int[] vector1;
    private final int[] vector2;
    private final int start;
    private final int batchSize;
    private long hamming;

    public ProcessThread(int[] vector1, int[] vector2, int start, int batchSize) {
//        Get values
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.start = start;
        this.batchSize = batchSize;
    }

//    Get Hamming value
    public long getHamming() {
        return hamming;
    }

//    Set Hamming value
    public void setHamming(long hamming) {
        this.hamming = hamming;
    }

    @Override
    public void run() {
//        Run hamming function and save the hamming
        setHamming(this.hamming = hamming(vector1, vector2, batchSize, start));
        System.out.println(getName() + " finished.");
    }

//    Calculate the hamming
    public long hamming(int[] vector1, int[] vector2, int batchSize, int start) {
        long sumHamming = 0;
        for (int i = start; i < start + batchSize; i++) {
            if (vector1[i] != vector2[i]) {
                sumHamming++;
            }
        }
        return sumHamming;
    }
}
