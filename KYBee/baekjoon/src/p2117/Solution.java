package p2117;

import java.util.*;
import java.io.*;

public class Solution {
    static final int[] dr = {0, -1, 0, 1};
    static final int[] dc = {-1, 0, 1, 0};

    static int TC;
    static int N, M;
    static int answer;
    static int[][][] dp;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p2117/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            answer = 0;
            map = new int[N][N];
            dp = new int[12][N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println(":here");
            for (int j = 0; j < N; j++) {
                System.out.println(Arrays.toString(map[j]));
            }
            System.out.println();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bfs(0, 0);
                }
            }

            sb.append("#" + tc + " " + answer + "\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int r, int c) {
        Queue<Integer[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Integer[] {r, c, 0});
        visited[r][c] = true;
        dp[0][r][c] = map[r][c] * M;

        while (!q.isEmpty()) {
            Integer[] current = q.poll();
            int curR = current[0];
            int curC = current[1];
            int weight = current[2];

            if (weight >= 12) continue;

            dp[weight][r][c] += map[curR][curC] * M;

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                    if (!visited[newR][newC]) {
                        visited[newR][newC] = true;
                        q.add(new Integer[] {newR, newC, weight + 1});
                    }
                }
            }
        }

        int total = 0;
        for (int i = 0; i < 12; i++) {
            total += dp[i][r][c];
            System.out.println(total);
            answer = Math.max(answer, total - (i * i + (i - 1) * (i - 1)));
            System.out.println(answer);
            System.out.println();
        }

        System.out.println(answer);
    }
}
