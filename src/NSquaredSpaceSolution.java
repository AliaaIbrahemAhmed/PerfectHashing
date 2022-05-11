import java.util.Random;
import java.util.Vector;

public class NSquaredSpaceSolution {
    private final int u = 32;
    private int n, b;
    private int[] output, S;
    private boolean[] isFull;
    private int[][] hashFunction;

    public NSquaredSpaceSolution(int[] S) {
        this.n = S.length;
        this.S = S;
        this.b = (int) Math.floor(Math.log(n * n) / Math.log(2));
        this.output = new int[n * n];
        this.isFull = new boolean[n * n];
        this.hashFunction = new int[b][u];
        generateOutput();
    }

    private int[][] generateRandomH() {
        int[][] h = new int[this.b][this.u];
        Random randNum = new Random();
        for (int i = 0; i < this.b; i++) {
            for (int j = 0; j < this.u; j++) {
                if (randNum.nextDouble() < 0.5) {
                    h[i][j] = 0;
                } else {
                    h[i][j] = 1;
                }
            }
        }
        return h;
    }

    private int[] getBinaryRep(int n) {
        int[] res = new int[32];
        int counter = 0;
        while (n != 0) {
            res[counter] = n % 2;
            n /= 2;
            counter++;
        }
        return res;
    }

    private int[] multiplyMatrices(int[][] h /* (b * u) */, int[] x /* 32 */) {
        int[] res = new int[this.b];
        for (int i = 0; i < this.b; i++) {
            for (int j = 0; j < this.u; j++) {
                res[i] += h[i][j] * x[31 - j];
            }
            res[i] %= 2;
        }
        return res;
    }

    private int getIndex(int[] binaryIndex) {
        int res = 0;
        for (int i = 0; i < binaryIndex.length; i++) {
            res += binaryIndex[i] * Math.pow(2, i);
        }
        return res;
    }

    private void generateOutput() {
        int i = 0;
        this.hashFunction = generateRandomH();
        while (i < this.S.length) {
            int[] x = getBinaryRep(this.S[i]);
            int[] binaryIndex = multiplyMatrices(this.hashFunction, x);
            int index = getIndex(binaryIndex);
            if (!this.isFull[index]) {
                this.output[index] = this.S[i];
                this.isFull[index] = true;
                i++;
            } else {
                System.out.println("collision");
                this.hashFunction = generateRandomH();
                this.output = new int[this.n * this.n];
                this.isFull = new boolean[this.n * this.n];
                i = 0;
            }

        }
    }

    public int lookUp(int element) {
        int index;
        int[] binaryInd;
        int[] x = getBinaryRep(element);
        binaryInd = multiplyMatrices(this.hashFunction, x);
        index = getIndex(binaryInd);
        if (this.output[index] == element && this.isFull[index]) return index;
        return -1;
    }
/*
    public void insert(int element) {
        int[] newOutput = new int[(this.n + 1) * (this.n + 1)];
        boolean[] newIsFull = newIsFull = new boolean[(this.n + 1) * (this.n + 1)];
        int[][] h;
        int size = this.hashFunctions.size();
        System.arraycopy(this.output, 0, newOutput, 0, this.output.length);
        System.arraycopy(this.isFull, 0, newIsFull, 0, this.isFull.length);
        this.output = newOutput;
        this.isFull = newIsFull;
        if (size == 0) {
            h = generateRandomH();
            this.hashFunctions.add(h);
        } else h = this.hashFunctions.get(size - 1);
        while (true) {
            int[] x = getBinaryRep(element);
            int[] binaryIndex = multiplyMatrices(h, x);
            int index = getIndex(binaryIndex);
            if (!this.isFull[index]) {
                this.output[index] = element;
                this.isFull[index] = true;
                break;
            } else {
                h = generateRandomH();
                this.hashFunctions.add(h);
            }
        }
    }

    public void delete(int element) {
        int index = this.lookUp(element);
        if (index != -1) {
            this.output[index] = 0;
            this.isFull[index] = false;
        }
    }
    */

}
