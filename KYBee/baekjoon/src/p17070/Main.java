package p17070;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {0, 1, 1};
    static final int[] dc = {1, 1, 0};
    static Map<Integer, Integer[]> delta = new HashMap<>();

    static int N;
    static int startR, startC, endR, endC;
    static int[][] map;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p17070/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        startR = 1; startC = 2; endR = N; endC = N;
        int dir = 0;

        // 0 오른쪽 1 대각선 2 아래
        delta.put(0, new Integer[] {0, 1});
        delta.put(1, new Integer[] {0, 1, 2});
        delta.put(2, new Integer[] {1, 2});

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    map[i][j] = 1;
                }
            }
        }
        dfs(startR, startC, dir);
        System.out.println(answer);
    }

    public static void dfs(int r, int c, int dir) {
        visited[r][c] = true;

        if (r == endR && c == endC) {
            answer++;
            return;
        }

        search: for (Integer d : delta.get(dir)) {
            if (d == 1) {
                for (int i = 0; i < 3; i++) {
                    int newR = r + dr[i];
                    int newC = c + dc[i];
                    if (!(1 <= newR && newR <= N && 1 <= newC && newC <= N && map[newR][newC] == 0)) {
                        continue search;
                    }
                }
            } else {
                int newR = r + dr[d];
                int newC = c + dc[d];
                if (!(1 <= newR && newR <= N && 1 <= newC && newC <= N && map[newR][newC] == 0)) {
                    continue search;
                }
            }

            int newR = r + dr[d];
            int newC = c + dc[d];
            dfs(newR, newC, d);
        }
    }
}