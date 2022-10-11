package hw.p2636;

import java.util.*;
import java.io.*;

public class Main_BJ_2636_치즈_김영빈_DFS {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, M;
    static int[][] cheeze;
    static boolean[][] visited;
    static int answer;
    static int count;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p2636/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheeze = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                cheeze[r][c] = Integer.parseInt(st.nextToken());
                if (cheeze[r][c] == 1) count++;
            }
        }

        int before = 0;
        while(true) {
            count = 0;
            visited = new boolean[N][M];

            goMelt(0, 0);

            if(count == 0) {
                break;
            } else {
                answer++;
                before = count;
            }
        }

        System.out.println(answer);
        System.out.println(before);
    }

    public static void goMelt(int r, int c) {
        visited[r][c] = true;

        if (cheeze[r][c] == 1) {
            count++;
            cheeze[r][c] = 0;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];

            if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                if (!visited[newR][newC]) {
                    goMelt(newR, newC);
                }
            }
        }
    }
}
