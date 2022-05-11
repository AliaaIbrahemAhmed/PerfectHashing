import java.util.*;

public class NSpaceSolution {
    private final int u = 32;
    private int n, b;
    private int[] output, S;
    private ArrayList<Integer>[] secondlevel;
    private boolean[]  isFull;
    private Vector<int[][]>HashFunctions;
    private HashMap map;
    NSpaceSolution(int [] S){
        this.n = S.length;
        this.S = S;
        this.b = (int) Math.ceil(Math.log(n) / Math.log(2));
        this.output = new int[n*n];
        this.secondlevel=new ArrayList [n];
         this.isFull=new boolean[n*n];
        this.HashFunctions = new Vector<>();
        this.map=new HashMap<Integer,Integer>();
        for(int i=0;i<n;i++){
            secondlevel[i]=new ArrayList<>();
        }
        generateOutput();

    }
    private int[][] generateRandomH() {
        int[][] h = new int [this.b][this.u];
        Random randNum = new Random();
        for (int i = 0; i < this.b; i++) {
            for (int j = 0; j < this.u; j++) {
                int x = randNum.nextInt(3);
                if (x % 2 == 0) {
                    h[i][j]=0 ;
                } else h[i][j] =1;
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
                if (h[i][j]* x[j] == 1) {
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
    private ArrayList<Integer>[] generateFirstLevel() {
        int[][] h = generateRandomH();
        HashFunctions.add(h);
        for (int i = 0; i < this.n; i++) {
            boolean flag = false;
            while (!flag) {
                int[] x = getBinaryRep(this.S[i]);
                int[] binaryIndex = multiplyMatrices(h, x);
                int index = getIndex(binaryIndex);
                if(!this.isFull[index]){
                    map.put(index,i);
                    this.output[index] = this.S[i];
                    this.isFull[index]=true;
                    flag=true;
                }
                else {
                    int location= (int) map.get(index);
                    this.secondlevel[location].add(this.S[i]);
                    flag=true;
                }
                }
            }
      /*  for (int i=0;i<secondlevel.length;i++) {

            System.out.println(secondlevel[i]);
            if(secondlevel[i].isEmpty()){
                System.out.println("yes");
            }
        }*/
        return secondlevel;
        }
        public void generateOutput(){
            ArrayList<Integer>[] firstLevel=  generateFirstLevel();


            for (int i=0;i<n;i++) {
                if (!firstLevel[i].isEmpty()) {
                    Integer[] h = new Integer[firstLevel[i].size()];
                    Object[] v = new Object[firstLevel[i].size()];
                    v = firstLevel[i].toArray();
                    System.arraycopy(v, 0, h, 0, v.length);
                    int[] z = new int[firstLevel[i].size()];
                    for (int k=0;k<h.length;k++) {
                        System.out.println(h[k]);
                    }
                    NSquaredSpaceSolution x = new NSquaredSpaceSolution(z);

                }
            }

            }

        }


