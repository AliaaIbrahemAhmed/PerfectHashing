import java.util.*;


public class NSpaceSolution {
    private final int u = 32;
    private int n, b;
    private int[] output, S;
    private ArrayList<Integer>[] secondlevel;
    private boolean[]  isFull;
    private ArrayList<int[][]>[]HashFunctions;
    private HashMap map;
    private  int[][] Hashfunction;
    private ArrayList<int[]>[] secondHashTable;
    private  HashMap<Integer,int[]> finalanswer;

    NSpaceSolution(int [] S){
        this.n = S.length;
        this.S = S;
        this.b = (int) Math.ceil(Math.log(n) / Math.log(2));
        this.output = new int[n];
        this.secondlevel=new ArrayList [n];
        this.isFull=new boolean[n];
        this.HashFunctions = new ArrayList[n];
        this.map=new HashMap<Integer,Integer>();
        this.Hashfunction=new int[this.b][this.u];
        this.secondHashTable=new ArrayList[n];
        this.finalanswer=new HashMap<>();
        for(int i=0;i<n;i++){
            secondlevel[i]=new ArrayList<>();
        }
        for (int i=0;i<n;i++){
            HashFunctions[i]=new ArrayList<>();
        }
        for (int i=0;i<n;i++){
            secondHashTable[i]=new ArrayList<>();
        }
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
        int[] res = new int[h.length];
        for (int i = 0; i < h.length; i++) {
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
    private ArrayList<Integer>[] generateFirstLevel() {
         Hashfunction = generateRandomH();
       // HashFunctions.add(h);
        for (int i = 0; i < this.n; i++) {
            boolean flag = false;
            while (!flag) {
                int[] x = getBinaryRep(this.S[i]);
                int[] binaryIndex = multiplyMatrices(Hashfunction, x);
                int index = getIndex(binaryIndex);
                if(!this.isFull[index]){
                    this.output[index] = this.S[i];
                    this.isFull[index]=true;
                    flag=true;
                }
                else {
                    this.secondlevel[index].add(this.S[i]);
                    flag=true;
                }
                }
            }
        return secondlevel;
        }
        public void generateOutput(){
            ArrayList<Integer>[] firstLevel=  generateFirstLevel();
                     for (int i=0 ;i<this.n;i++) {
                         int newinput[] = new int[firstLevel[i].size()];
                         System.out.println(firstLevel[i]);
                         if (!firstLevel[i].isEmpty()) {
                             for (int j = 0; j < firstLevel[i].size(); j++) {
                                 newinput[j] = firstLevel[i].get(j);
                             }

                              NSquaredSpaceSolution finalsolution = new NSquaredSpaceSolution(newinput);
                             this.HashFunctions[i].add(finalsolution.hashFunction);
                             secondHashTable[i].add(finalsolution.output);

                         }
                     }
            }
            public void lookup(int element) {
                int index;
                int[] binaryInd;
                int[] x = getBinaryRep(element);
                binaryInd = multiplyMatrices(Hashfunction, x);
                index = getIndex(binaryInd);
                System.out.println(index + "kkk");
                int[] secondBinaryindex;
                if (this.output[index] == element) {
                    System.out.println("buldum ilk hash :)");
                } else {
                    if (!this.HashFunctions[index].isEmpty()) {
                        System.out.println(HashFunctions[index]);

                        secondBinaryindex = multiplyMatrices(this.HashFunctions[index].get(0), x);
                        int secondindex = getIndex(secondBinaryindex);
                        int[] out = secondHashTable[index].get(0);
                        if (out[secondindex] == element) {
                            System.out.println("buldum ikinci hash :)");
                        } else {
                            System.out.println("sorry doesn't exist :(");
                        }
                    }


                }
            }
}



