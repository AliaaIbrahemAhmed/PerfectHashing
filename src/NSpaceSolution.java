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
        this.hashFunctionOfMethod2 = new int[this.b][this.u];
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
        /**...Generate Random Hash Function**/
        hashFunctionOfMethod2 = generateRandomH(this.b, this.u);
        for (int i = 0; i < this.n; i++) {
            boolean flag = false;
            while (!flag) {
                int[] x = getBinaryRep(this.S[i]);
                int[] binaryIndex = multiplyMatrices(hashFunctionOfMethod2, x);
                int index = getIndex(binaryIndex);
                /**If there is no collision put this number in first level hash**/
                if (!this.isFull[index]) {
                    this.output[index] = this.S[i];
                    this.isFull[index] = true;
                } else { //else put the number made collision in array of number that made collision with the same number
                    this.secondLevel[index].add(this.S[i]);
                }
                flag = true;
            }
        }
        return secondLevel;
    }
       /**To generate second level Hash**/
    public void generateOutput() {
        ArrayList<Integer>[] firstLevel = generateFirstLevel();
        for (int i = 0; i < this.n; i++) {
            int newInput[] = new int[firstLevel[i].size()];
            if (!firstLevel[i].isEmpty()) {
                for (int j = 0; j < firstLevel[i].size(); j++) {
                    newInput[j] = firstLevel[i].get(j);
                }
                /**Apply first method on the vector of collision**/
                NSquaredSpaceSolution finalSolution = new NSquaredSpaceSolution(newInput);
                /**Store Hash function of Each Vector**/
                this.HashFunctions[i].add(finalSolution.getHashFunction());
                /**Store second Hash Table of Each Vector **/
                secondHashTable[i].add(finalSolution.getOutput());

            }
        }
    }

    public void lookUp(int element) {
        int i;
        int[] binaryInd;
        int[] x = getBinaryRep(element);
        binaryInd = multiplyMatrices(hashFunctionOfMethod2, x);
        i = getIndex(binaryInd); // i=h(x)
        int[] secondBinaryIndex;
        /**check if the element in the first hash level**/
        if (this.output[i] == element) {
            System.out.println("Found in first level.");
        } else {//if not check in the second hash level
            if (!this.HashFunctions[i].isEmpty()) {
                secondBinaryIndex = multiplyMatrices(this.HashFunctions[i].get(0)/*hi*/, x);//hi(x)
                int secondIndex = getIndex(secondBinaryIndex);
                int[] out = secondHashTable[i].get(0);//Ai
                if (out[secondIndex] == element) {//Ai[hi(x)]
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


