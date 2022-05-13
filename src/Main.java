import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int n = sc.nextInt();
        int[] S = new int[n];
        System.out.println("Enter the values of the array");
        for (int i = 0; i < n; i++) {
            S[i] = sc.nextInt();
        }
        S = Arrays.stream(S).distinct().toArray();
        NSquaredSpaceSolution nSquaredSpaceSolution = new NSquaredSpaceSolution(S);
        System.out.println("First Method: ");
        System.out.println("number of rebuild hash of method 1 = " + nSquaredSpaceSolution.getCollisions());
        nSquaredSpaceSolution.printHashTable();
        System.out.println("Second Method: ");
        NSpaceSolution nSpaceSolution = new NSpaceSolution(S);

    }
}
