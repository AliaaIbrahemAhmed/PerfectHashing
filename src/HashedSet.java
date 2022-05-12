import java.util.Random;

public abstract class HashedSet {
    protected int u = 32;
    protected int n, b;
    protected int[] output, S;
    protected int[][] hashFunction;
    protected boolean[] isFull;
   protected int counter;

    protected static int[][] generateRandomH(int b, int u) {
        int[][] h = new int[b][u];
        Random randNum = new Random();
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < u; j++) {
                if (randNum.nextDouble() < 0.5) {
                    h[i][j] = 0;
                } else {
                    h[i][j] = 1;
                }
            }
        }
        return h;
    }

    protected static int[] getBinaryRep(int n) {
        int[] res = new int[32];
        int counter = 0;
        while (n != 0) {
            res[counter] = n % 2;
            n /= 2;
            counter++;
        }
        return res;
    }

    protected static int[] multiplyMatrices(int[][] h /* (b * u) */, int[] x /* 32 */) {
        int[] res = new int[h.length];
        for (int i = 0; i < h.length; i++) {
            for (int j = 0; j < 32; j++) {
                res[i] += h[i][j] * x[31 - j];
            }
            res[i] %= 2;
        }
        return res;
    }

    protected static int getIndex(int[] binaryIndex) {
        int res = 0;
        for (int i = 0; i < binaryIndex.length; i++) {
            res += binaryIndex[i] * Math.pow(2, i);
        }
        return res;
    }
    public abstract void lookUp(int element);
}
