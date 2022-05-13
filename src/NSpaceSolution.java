import java.util.*;


public class NSpaceSolution extends HashedSet {
    private int sum;
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
        this.sum=0;
        this.secondHashTable=new ArrayList[n];
        for (int i = 0; i < n; i++) {
            secondLevel[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            HashFunctions[i] = new ArrayList<>();
        }
        for (int i=0;i<n;i++){
            secondHashTable[i]=new ArrayList<>();
        }
        generateOutput();

    }


    private ArrayList<Integer>[] generateFirstLevel() {
        /**...Generate Random Hash Function**/
        hashFunction = generateRandomH(this.b, this.u);
        for (int i = 0; i < this.n; i++) {
                int[] x = getBinaryRep(this.S[i]);
                int[] binaryIndex = multiplyMatrices(hashFunction, x);
                int index = getIndex(binaryIndex);
                    this.secondLevel[index].add(this.S[i]);
        }
        for (int i=0;i<this.n;i++) {
            if (!this.secondLevel[i].isEmpty()) {
                sum += secondLevel[i].size() * secondLevel[i].size();
            }
        }
        System.out.println("Total space "+this.sum);

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
                System.out.println("in table number "+i);
                finalSolution.printHashTable();
                System.out.println("Number Of Times Required to Rebuild Hash Table (collision) = "+finalSolution.getCollisions());
                System.out.println("_____________________________");


            }
        }
    }

    public void lookUp(int element) {
        int i;
        int[] binaryInd;
        int[] x = getBinaryRep(element);
        binaryInd = multiplyMatrices(hashFunction, x);
        i = getIndex(binaryInd); // i=h(x)
        int[] secondBinaryIndex;
        /**check if the element in the first hash level**/
         //if not check in the second hash level
            if (!this.HashFunctions[i].isEmpty()) {
                secondBinaryIndex = multiplyMatrices(this.HashFunctions[i].get(0)/*hi*/, x);//hi(x)
                int secondIndex = getIndex(secondBinaryIndex);
                int[] out = secondHashTable[i].get(0);//Ai
                if (out[secondIndex] == element) {//Ai[hi(x)]
                    System.out.println("Number "+element+" Found In Hash Table Number "+i+" At Index "+secondIndex);
                } else {
                    System.out.println("Not Found!");
                }
            }
            else{
                System.out.println("Not Found!");
            }
        }
    }



