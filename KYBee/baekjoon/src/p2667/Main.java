package p2667;

import java.util.*;
import java.io.*;

public class Main {

    public static final int[] dr = {-1, 0, 1, 0};
    public static final int[] dc = {0, -1, 0, 1};
    public static List<Integer> result = new ArrayList<>();

    public static int N;
    public static int answer;
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = line.charAt(c) - '0';
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != 0 && visited[r][c] != true) {
                    //아직 안 가본 곳이라서 간다.
                    answer = 0;
                    go(r, c);
                    result.add(answer);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int r: result) {
            System.out.println(r);
        }
    }

    public static void go(int r, int c) {
        answer++;
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];

            if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                if (map[newR][newC] != 0 && !visited[newR][newC]) {
                    go(newR, newC);
                }
            }
        }
    }
}