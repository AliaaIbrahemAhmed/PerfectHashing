import java.util.Random;
import java.util.Vector;

public class NSquaredSpaceSolution extends HashedSet {

    public NSquaredSpaceSolution(int[] S) {
        this.n = S.length;
        this.S = S;
        this.b = (int) Math.floor(Math.log(n * n) / Math.log(2));
        this.output = new int[n * n];
        this.isFull = new boolean[n * n];
        this.hashFunction = new int[b][u];
        generateOutput();
    }

    public int[] getOutput() {
        return output;
    }

    public int[][] getHashFunction() {
        return hashFunction;
    }


    private void generateOutput() {
        int i = 0;
        this.hashFunction = generateRandomH(this.b, this.u);
        while (i < this.S.length) {
            int[] x = getBinaryRep(this.S[i]);
            int[] binaryIndex = multiplyMatrices(this.hashFunction, x);
            int index = getIndex(binaryIndex);
            if (!this.isFull[index]) {
                this.output[index] = this.S[i];
                this.isFull[index] = true;
                i++;
            } else {
                this.hashFunction = generateRandomH(this.b, this.u);
                this.output = new int[this.n * this.n];
                this.isFull = new boolean[this.n * this.n];
                i = 0;
            }

        }
    }


    public void lookUp(int element) {
        int index;
        int[] binaryInd;
        int[] x = getBinaryRep(element);
        binaryInd = multiplyMatrices(this.hashFunction, x);
        index = getIndex(binaryInd);
        if (this.output[index] == element && this.isFull[index]) System.out.println("Found.");
        else System.out.println("Not Found!");
    }
}
