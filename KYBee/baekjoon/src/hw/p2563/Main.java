package hw.p2563;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] paper;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        paper = new int[101][101];

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int r = x; r <= x + 9; r++) {
                for (int c = y; c <= y + 9; c++) {
                    paper[r][c] = 1;
                }
            }
        }

        int total = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                total += paper[i][j];
            }
        }

        System.out.println(total);
    }

}
