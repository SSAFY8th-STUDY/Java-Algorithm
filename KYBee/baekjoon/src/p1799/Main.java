package p1799;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dr = {-1, -1, 1, 1};
    static final int[] dc = {-1, 1, -1, 1};

    static boolean[][] visited;

    static int N;
    static int[][] map;

    static int black;
    static int white;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p1799/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        // 놓을 수 있으면 1, 없으면 0
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getBlack(0, 0, 0);
        getWhite(0, 1, 0);

        System.out.println(black + white);
    }

    public static void getBlack(int r, int c, int cnt) {
        //좌표 수정
        black = Math.max(black, cnt);

        if (c >= N) {
            r += 1;
            if (r % 2 == 0) c = 0;
            else c = 1;
        }
        if (r >= N) return;

        if (isAble(r, c) && map[r][c] == 1) {
            visited[r][c] = true;
            getBlack(r, c + 2, cnt + 1);
            visited[r][c] = false;
        }

        getBlack(r, c + 2, cnt);
    }

    public static void getWhite(int r, int c, int cnt) {
        white = Math.max(white, cnt);

        //좌표 수정
        if (c >= N) {
            r += 1;
            if (r % 2 == 0) c = 1;
            else c = 0;
        }
        if (r >= N) return;

        if (isAble(r, c) && map[r][c] == 1) {
            visited[r][c] = true;
            getWhite(r, c + 2, cnt + 1);
            visited[r][c] = false;
        }

        getWhite(r, c + 2, cnt);
    }

    public static boolean isAble(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int curR = r + dr[i];
            int curC = c + dc[i];

            while (0 <= curR && curR < N && 0 <= curC && curC < N) {
                if (visited[curR][curC]) return false;
                else {
                    curR += dr[i];
                    curC += dc[i];
                }
            }
        }

        return true;
    }
}