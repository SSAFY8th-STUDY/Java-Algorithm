package p1012;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int TC;
    static int M, N, K;
    static int answer;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p1012/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            answer = 0;

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                map[r][c] = 1;
            }

            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] != 0) {
                        bfs(r, c);
                        answer += 1;
                    }
                }
            }

            System.out.println(answer);
        }
    }

    public static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        map[r][c] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int newR = current[0] + dr[i];
                int newC = current[1] + dc[i];

                if (0 <= newR && newR < M && 0 <= newC && newC < N) {
                    if (map[newR][newC] != 0) {
                        map[newR][newC] = 0;
                        q.add(new int[] {newR, newC});
                    }
                }
            }
        }
    }
}
