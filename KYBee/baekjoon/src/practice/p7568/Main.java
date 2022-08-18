package practice.p7568;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] people;
    static int[] rank;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/practice/p7568/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        people = new int[N][2];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            people[i][0] = Integer.parseInt(st.nextToken());
            people[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0 ; i < N ; i++) {
            int curRank = 1;
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    if (people[i][0] < people[j][0] && people[i][1] < people[j][1]) {
                        curRank += 1;
                    }
                }
            }
            rank[i] = curRank;
        }

        for (int i = 0 ; i < N ; i++) {
            System.out.print(rank[i] + " ");
        }
        System.out.println();
    }
}
