package p2206;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p2206/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[2][N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        System.out.println(getPath(1, 1));
    }

    public static int getPath(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        //{r인덱스, c인덱스, 지금까지 온 거리, 부실 수 있는 횟수}
        q.add(new int[] {r, c, 1, 1});

        for (int i = 0; i < 2; i++) {
            visited[i][r][c] = true;
        }

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int curR = current[0]; int curC = current[1]; int dist = current[2]; int count = current[3];

            if (curR == N && curC == M) return dist;

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (1 <= newR && newR <= N && 1 <= newC && newC <= M) {
                    if (map[newR][newC] == 0 && visited[count][newR][newC] == false) {
                        visited[count][newR][newC] = true;
                        q.add(new int[] {newR, newC, dist + 1, count});
                    } else if (map[newR][newC] == 1 && count > 0) {
                        if (visited[count - 1][newR][newC] == false) {
                            visited[count - 1][newR][newC] = true;
                            q.add(new int[] {newR, newC, dist + 1, count - 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
}