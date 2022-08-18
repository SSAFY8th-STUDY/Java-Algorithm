package p7576;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, M;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static int answer = 0;
    static int count = 0;
    static int empty = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p7576/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    q.add(new int[] {i, j});
                    count++;
                } else if (map[i][j] == -1) {
                    empty++;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int newR = current[0] + dr[i];
                int newC = current[1] + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    if (map[newR][newC] == 0) {
                        map[newR][newC] = map[current[0]][current[1]] + 1;
                        count++;
                        answer = Math.max(answer, map[current[0]][current[1]]);
                        q.add(new int[] {newR, newC});
                    }
                }
            }
        }

        if (count == N * M - empty) System.out.println(answer);
        else System.out.println(-1);
    }
}