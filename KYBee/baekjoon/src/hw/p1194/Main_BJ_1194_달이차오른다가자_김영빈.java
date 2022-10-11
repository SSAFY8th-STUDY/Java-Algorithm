package hw.p1194;

import java.util.*;
import java.io.*;

public class Main_BJ_1194_달이차오른다가자_김영빈 {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] start;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p1194/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[64][N][M];
        start = new int[2];

        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = line.charAt(c);

                if (map[r][c] == '0') {
                    start[0] = r; start[1] = c;
                    map[r][c] = 0;
                } else if (map[r][c] == '.') {
                    map[r][c] = 0;
                } else if ('a' <= map[r][c] && map[r][c] <= 'f') {
                    int key = map[r][c] - 'a';
                    map[r][c] = -1 * 1 << key;
                } else if ('A' <= map[r][c] && map[r][c] <= 'F') {
                    int key = map[r][c] - 'A';
                    map[r][c] = 1 << key;
                } else if (map[r][c] == '1') {
                    map[r][c] = 1000;
                } else if (map[r][c] == '#') {
                    map[r][c] = -1000;
                }
            }
        }

        goToMoon();

        System.out.println(answer);
    }

    public static void goToMoon() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {start[0], start[1], 0, 0});
        visited[0][start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curR = current[0];
            int curC = current[1];
            int flag = current[2];
            int cnt = current[3];

            if (map[curR][curC] == 1000) {
                answer = cnt;
                return;
            } else if (map[curR][curC] < 0 && map[curR][curC] != -1000) {
                for (int i = 0; i < 6; i++) {
                    if (((-1 * map[curR][curC]) & (1 << i)) != 0) {
                        flag = flag | (1 << i);
                        break;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    if (!visited[flag][newR][newC]) {
                        if (map[newR][newC] != -1000) {
                            if (map[newR][newC] == 1000 || map[newR][newC] <= 0) {
                                visited[flag][newR][newC] = true;
                                q.add(new int[] {newR, newC, flag, cnt + 1});
                            } else if (map[newR][newC] > 0) {
                                int key = map[newR][newC];
                                if ((flag & key) != 0) {
                                    visited[flag][newR][newC] = true;
                                    q.add(new int[] {newR, newC, flag, cnt + 1});
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}