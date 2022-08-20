package p16234;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, L, R;
    static int[][] map;
    static int[][] move;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p16234/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        move = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (movable()) {
            // 이동을 함
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) bfs(i, j);
                }
            }

            // 초기화
            for (int i = 0; i < N; i++) Arrays.fill(move[i], 0);
            answer++;
        }

        System.out.println(answer);
    }

    public static boolean movable() {
        boolean isAble = false;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                //각 칸을 모두 4방탐색 -> 갈 수 있는지 판단
                int current = map[r][c];
                int flag = 0;

                for (int i = 0; i < 4; i++) {
                    int newR = r + dr[i];
                    int newC = c + dc[i];

                    if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                        int neigh = map[newR][newC];
                        int diff = Math.abs(neigh - current);
                        if (L <= diff && diff <= R) {
                            // 이동할 수 있도록 열어둠
                            flag = flag | 1 << i;
                            isAble = true;
                        }
                    }
                }
                move[r][c] = flag;
            }
        }
        return isAble;
    }

    public static void bfs(int r, int c) {
        Queue<Integer[]> q = new LinkedList<>();
        Queue<Integer[]> movingQ = new LinkedList<>();

        visited[r][c] = true;
        int total = map[r][c];
        int countryCnt = 1;

        q.add(new Integer[] {r, c});
        movingQ.add(new Integer[] {r, c});

        // 총합을 우선 구함
        while (!q.isEmpty()) {

            Integer[] current = q.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                    // 갈 수 있고, 아직 안 갔으면
                    if (!visited[newR][newC] && (move[curR][curC] & (1 << i)) != 0) {
                        visited[newR][newC] = true;
                        total += map[newR][newC];
                        countryCnt++;

                        q.add(new Integer[] {newR, newC});
                        movingQ.add(new Integer[] {newR, newC});
                    }
                }
            }
        }

        // 실제로 이동을 함
        int targetPeople = (int) Math.floor(total / countryCnt);
        while (! movingQ.isEmpty()) {
            Integer[] current = movingQ.poll();
            map[current[0]][current[1]] = targetPeople;
        }
    }
}