import java.util.*;


public class NSpaceSolution extends HashedSet {

    private final ArrayList<Integer>[] secondLevel;
    private ArrayList<int[][]>[] HashFunctions;
    private ArrayList<int[]>[] secondHashTable;

    NSpaceSolution(int[] S) {
        this.n = S.length;
        this.S = S;
        this.b = (int) Math.floor(Math.log(n) / Math.log(2));
        this.output = new int[n];
        this.secondLevel = new ArrayList[n];
        this.isFull = new boolean[n];
        this.HashFunctions = new ArrayList[n];
        this.hashFunction = new int[this.b][this.u];
        this.secondHashTable = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            secondLevel[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            HashFunctions[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            secondHashTable[i] = new ArrayList<>();
        }
        generateOutput();

    }


    private ArrayList<Integer>[] generateFirstLevel() {
        hashFunction = generateRandomH(this.b, this.u);
        for (int i = 0; i < this.n; i++) {
            boolean flag = false;
            while (!flag) {
                int[] x = getBinaryRep(this.S[i]);
                int[] binaryIndex = multiplyMatrices(hashFunction, x);
                int index = getIndex(binaryIndex);
                if (!this.isFull[index]) {
                    this.output[index] = this.S[i];
                    this.isFull[index] = true;
                } else {
                    this.secondLevel[index].add(this.S[i]);
                }
                flag = true;
            }
        }
        return secondLevel;
    }

    public void generateOutput() {
        ArrayList<Integer>[] firstLevel = generateFirstLevel();
        for (int i = 0; i < this.n; i++) {
            int newInput[] = new int[firstLevel[i].size()];
            if (!firstLevel[i].isEmpty()) {
                for (int j = 0; j < firstLevel[i].size(); j++) {
                    newInput[j] = firstLevel[i].get(j);
                }

                NSquaredSpaceSolution finalSolution = new NSquaredSpaceSolution(newInput);
                this.HashFunctions[i].add(finalSolution.getHashFunction());
                secondHashTable[i].add(finalSolution.getOutput());

            }
        }
    }

    public void lookUp(int element) {
        int index;
        int[] binaryInd;
        int[] x = getBinaryRep(element);
        binaryInd = multiplyMatrices(hashFunction, x);
        index = getIndex(binaryInd);
        int[] secondBinaryIndex;
        if (this.output[index] == element) {
            System.out.println("Found in first level.");
        } else {
            if (!this.HashFunctions[index].isEmpty()) {
                secondBinaryIndex = multiplyMatrices(this.HashFunctions[index].get(0), x);
                int secondIndex = getIndex(secondBinaryIndex);
                int[] out = secondHashTable[index].get(0);
                if (out[secondIndex] == element) {
                    System.out.println("Found in second level.");
                } else {
                    System.out.println("Not Found!");
                }
            }
            else{
                System.out.println("Not Found!");
            }
        }
    }
}


