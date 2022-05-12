public class Main {
    public static void main(String[] args) {
        int[] S = new int[]{1, 2, 3, 4, 5, 6, 7,8,9,10,50,60,70,80,100,1000,10000};
        NSquaredSpaceSolution nSquaredSpaceSolution = new NSquaredSpaceSolution(S);
        System.out.println("number of rebuild hash of method 1= "+nSquaredSpaceSolution.counter);
        NSpaceSolution nSpaceSolution = new NSpaceSolution(S);
        for(int i : S){
            nSquaredSpaceSolution.lookUp(i);
            nSpaceSolution.lookUp(i);
        }
        nSquaredSpaceSolution.lookUp(100);
        nSpaceSolution.lookUp(100);
        nSquaredSpaceSolution.lookUp(22);
        nSpaceSolution.lookUp(22);


    }
}
