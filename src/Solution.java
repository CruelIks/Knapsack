import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Prog on 05.05.2016.
 */
public class Solution {

    public static void main(String args[]) throws IOException {

        System.out.println("Enter filename (first string - weight, second string - cost):");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        System.out.println("Enter capacity:");
        int capacity = scanner.nextInt();
        int countThings = 0;

        scanner.close();

        BufferedReader in = new BufferedReader(new FileReader(fileName));


        //weight things
        String[] weights = in.readLine().split(" ");

        //cost things
        String[] costs = in.readLine().split(" ");

        countThings = weights.length;

        int[][] matrix = new int[countThings + 1][capacity + 1];

        //generate matrix
        for (int i = 0; i <= capacity; i++) {
            matrix[0][i] = 0;
        }
        for (int i = 0; i <= countThings; i++) {
            matrix[i][0] = 0;
        }

        //
        for (int k = 1; k <= countThings; k++) {
            for (int s = 1; s <= capacity; s++) {

                int wk = Integer.parseInt(weights[k - 1]);
                int pk = Integer.parseInt(costs[k - 1]);

                if (s >= wk)    //Если текущий предмет вмещается в рюкзак
                    matrix[k][s] = Math.max(matrix[k - 1][s], matrix[k - 1][s - wk] + pk); //выбираем класть его или нет
                else
                    matrix[k][s] = matrix[k - 1][s];
            }
        }

        //show the matrix
        for (int i = 0; i <= countThings; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

        //find best things

        ArrayList<Integer> ans = new ArrayList<>();
        findAns(countThings, capacity, matrix, weights, ans);

        //show them
        System.out.println("Take the things number:");
        for (Integer an : ans) {
            System.out.print(an + " ");
        }

        //max costs
        System.out.println("");
        System.out.println("Max costs:");
        System.out.println(matrix[countThings][capacity]);

        in.close();
    }

    private static void findAns(int countThings, int capacity, int[][] matrix, String[] weights, ArrayList<Integer> ans) {

        if (matrix[countThings][capacity] == 0) return;

        if (matrix[countThings - 1][capacity] == matrix[countThings][capacity])
            findAns(countThings - 1, capacity, matrix, weights, ans);
        else {
            int wk = Integer.parseInt(weights[countThings - 1]);
            findAns(countThings - 1, capacity - wk, matrix, weights, ans);
            ans.add(countThings);
        }

    }
}
