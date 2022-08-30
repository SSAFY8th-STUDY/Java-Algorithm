package hw.p10971;

import java.util.*;
import java.io.*;

public class Main_BJ_10971_외판원순회2_김영빈 {

    static int N;
    static int[][] map;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p10971/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 0, 1);
        System.out.println(answer);
    }

    public static void go(int n, int dist, int cnt) {
        if (cnt == N) {
            if (map[n][0] != 0) {
                dist += map[n][0];
                answer = Math.min(answer, dist);
            }
            return;
        }
        if (dist >= answer) return;

        visited[n] = true;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && map[n][i] != 0) go(i, dist + map[n][i], cnt + 1);
        }
        visited[n] = false;
    }
}
