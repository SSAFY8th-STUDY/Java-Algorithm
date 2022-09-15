package p17779;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] temp;
    static int[] people;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p17779/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // x y d1 d2 선택
        for (int x = 1 ; x <= N; x++) {
            for (int y = 2; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    if (y - d1 < 1) break;

                    for (int d2 = 1; d2 <= N; d2++) {
                        // 구역 나누기
                        if (x + d1 + d2 > N || y + d2 > N) break;

                        temp = new int[N + 1][N + 1];
                        people = new int[6];
                        divideSection(x, y, d1, d2);

                        // 인원 계산 하기
                        calculate();
                        Arrays.sort(people);
                        answer = Math.min(answer, Math.abs(people[1] - people[5]));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static void divideSection(int x, int y, int d1, int d2) {
        //경계선
        int curX = x; int curY = y;
        while (1 <= curX && curX <= Math.min(N, x + d1) && Math.max(1, y - d1) <= curY && curY <= N) {
            temp[curX][curY] = 5;
            curX += 1; curY -= 1;
        }

        curX = x; curY = y;
        while (1 <= curX && curX <= Math.min(N, x + d2) && 1 <= curY && curY <= Math.min(N, y + d2)) {
            temp[curX][curY] = 5;
            curX += 1; curY += 1;
        }

        curX = x + d1; curY = y - d1;
        while (1 <= curX && curX <= Math.min(N, x + d1 + d2) && 1 <= curY && curY <= Math.min(N, y - d1 + d2)) {
            temp[curX][curY] = 5;
            curX += 1; curY += 1;
        }

        curX = x + d2; curY = y + d2;
        while (1 <= curX && curX <= Math.min(N, x + d2 + d1) && Math.max(1, y + d2 - d1) <= curY && curY <= N) {
            temp[curX][curY] = 5;
            curX += 1; curY -= 1;
        }

        //제 1선거구
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (temp[r][c] != 5) temp[r][c] = 1;
            }
        }

        //제 2 선거구
        for (int r = 1; r <= x + d2; r++) {
            for (int c = y + 1; c <= N; c++) {
                if (temp[r][c] != 5) temp[r][c] = 2;
            }
        }

        //제 3 선거구
        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (temp[r][c] != 5) temp[r][c] = 3;
            }
        }

        //제 4 선거구
        for (int r = x + d2 + 1; r <= N; r++) {
            for (int c = y - d1 + d2; c <= N; c++) {
                if (temp[r][c] != 5) temp[r][c] = 4;
            }
        }
    }

    public static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();

        visited[x][y] = true;
        q.add(new int[] {x, y});
        int total = map[x][y];

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (1 <= newR && newR <= N && 1 <= newC && newC <= N && !visited[newR][newC]) {
                    if (temp[x][y] == temp[newR][newC]) {
                        visited[newR][newC] = true;
                        total += map[newR][newC];
                        q.add(new int[] {newR, newC});
                    }
                }
            }
        }

        return total;
    }

    public static void calculate() {
        // 제 5선거구 먼저 처리하기
        visited = new boolean[N + 1][N + 1];
        Queue<int[]> q = new ArrayDeque<>();
        // 제 1 선거구
        people[1] = bfs(1, 1);

        // 제 2 선거구
        people[2] = bfs(1, N);

        // 제 3 선거구
        people[3] = bfs(N, 1);

        // 제 4 선거구
        people[4] = bfs(N, N);

        for (int r = 1 ; r <= N ; r++) {
            for (int c = 1 ; c <= N ; c++) {
                if (!visited[r][c]) {
                    people[5] += bfs(r, c);
                }
            }
        }
    }
}