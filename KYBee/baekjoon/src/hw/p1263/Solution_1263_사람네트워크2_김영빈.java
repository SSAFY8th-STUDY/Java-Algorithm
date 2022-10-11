package hw.p1263;

import java.util.*;
import java.io.*;

public class Solution_1263_사람네트워크2_김영빈 {
    static final int INF = Integer.MAX_VALUE;
    static int TC, N;
    static long[][] graph;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p1263/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            graph = new long[N][N];
            answer = INF;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if(i != j && graph[i][j] == 0) graph[i][j] = INF;
                }
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i == j) continue;
                        if (graph[i][j] > graph[i][k] + graph[k][j])
                            graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                int total = 0;
                for (int j = 0; j < N; j++) {
                    total += graph[i][j];
                }
                answer = Math.min(answer, total);
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}