package practice.p14499;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {1, -1, 0, 0};

    static int N, M;
    static int r, c;
    static int K;

    static int[][] graph;
    //1 3 5
    static int[][] dice = {{4, 1, 5}, {3, 6, 5}, {5, 3, 6}, {2, 3, 1}};
    static int[] diceStatus = new int[7];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p14499/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken()) - 1;
            //오른 왼 위 아래
            int newR = r + dr[command];
            int newC = c + dc[command];

            if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                rollDice(command);

                if (graph[newR][newC] == 0) {
                    graph[newR][newC] = diceStatus[6];
                } else {
                    diceStatus[6] = graph[newR][newC];
                    graph[newR][newC] = 0;
                }

                System.out.println(diceStatus[1]);
                r = newR; c = newC;
            }
        }
    }

    public static void rollDice(int command) {
        int[] direction = dice[command];
        int[] newStatus = new int[7];

        int one = direction[0];
        int right = direction[1];
        int bottom = direction[2];

        newStatus[one] = diceStatus[1];
        newStatus[7 - bottom] = diceStatus[2];
        newStatus[right] = diceStatus[3];
        newStatus[7 - right] = diceStatus[4];
        newStatus[bottom] = diceStatus[5];
        newStatus[7 - one] = diceStatus[6];

        diceStatus = newStatus.clone();
    }
}