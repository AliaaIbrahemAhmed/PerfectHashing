import java.util.Random;

public class NSquaredSpaceSolution {
    private final int u = 32;
    private int n, b;
    private int[] output, S;
    private boolean[] isFull;

    public NSquaredSpaceSolution(int[] S) {
        this.n = S.length;
        this.S = S;
        this.b = (int) Math.ceil(Math.log(n) / Math.log(2));
        this.output = new int[n * n];
        this.isFull = new boolean[n * n];
        generateOutput();
    }

    private int[][] generateRandomH() {
        int[][] h = new int[this.b][this.u];
        Random randNum = new Random();
        for (int i = 0; i < this.b; i++) {
            for (int j = 0; j < this.u; j++) {
                int x = randNum.nextInt(3);
                if (x % 2 == 0) h[i][j] = 0;
                else h[i][j] = 1;
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
                if (h[i][j] * x[j] == 1) {
                    res[i] = 1;
                    break;
                }
            }
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
        for (int i = 0; i < this.n; i++) {
            boolean flag = false;
            while (!flag) {
                int[][] h = generateRandomH();
                int[] x = getBinaryRep(this.S[i]);
                int[] binaryIndex = multiplyMatrices(h, x);
                int index = getIndex(binaryIndex);
                if (!this.isFull[index]) {
                    this.output[index] = this.S[i];
                    this.isFull[index] = true;
                    flag = true;
                }
            }
        }
    }

    public int lookUp(int element){
        int index = 0;
        

        return index;
    }

}
