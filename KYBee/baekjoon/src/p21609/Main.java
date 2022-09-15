package p21609;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N;
    static int[][] map;
    static int M;
    static int answer;
    static PriorityQueue<Block> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p21609/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) map[i][j] = M + 1;
            }
        }

        while(true) {
            findBlock();
            if (pq.size() == 0) break;

            deleteBlock(pq.peek());
            pq = new PriorityQueue<>();

            gravity();
            rotate();
            gravity();
        }

        System.out.println(answer);
    }

    public static void findBlock() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> rainbows = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 안가봤고 무지개가 아닐 때 만 간다
                if (!visited[i][j] && map[i][j] != M + 1 && map[i][j] != -1 && map[i][j] != 0) {
                    visited[i][j] = true;
                    q.add(new int[] {i, j});
                    int rainbowCnt = 0;
                    int startR = i;
                    int startC = j;
                    int totalCnt = 1;

                    while (!q.isEmpty()) {
                        int[] current = q.poll();
                        int curR = current[0];
                        int curC = current[1];

                        for (int d = 0; d < 4; d++) {
                            int newR = curR + dr[d];
                            int newC = curC + dc[d];

                            if (0 <= newR && newR < N && 0 <= newC && newC < N && !visited[newR][newC]) {
                                if (map[newR][newC] == map[i][j]) {
                                    visited[newR][newC] = true;
                                    totalCnt++;
                                    q.add(new int[] {newR, newC});
                                } else if (map[newR][newC] == M + 1) {
                                    rainbowCnt++;
                                    rainbows.add(new int[] {newR, newC});

                                    visited[newR][newC] = true;
                                    totalCnt++;
                                    q.add(new int[] {newR, newC});
                                }
                            }
                        }
                    }

                    // rainbow 방문을 다시 해제 -> 다른 색 공도 들어올 수 있도록
                    while (!rainbows.isEmpty()){
                        int[] current = rainbows.poll();
                        visited[current[0]][current[1]] = false;
                    }

                    // block 새로 하나 만들기
                    if (totalCnt < 2) continue;
                    pq.add(new Block(startR, startC, totalCnt, rainbowCnt));
                }
            }
        }
    }

    public static void deleteBlock(Block target) {
        int r = target.startR;
        int c = target.startC;
        int color = map[r][c];
        answer += target.cnt * target.cnt;

        Queue<int[]> q = new ArrayDeque<>();
        map[r][c] = 0;
        q.add(new int[] {r, c});

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4 ; i++){
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < N && map[newR][newC] != 0 && map[newR][newC] != -1) {
                    if (map[newR][newC] == M + 1 || map[newR][newC] == color) {
                        map[newR][newC] = 0;
                        q.add(new int[] {newR, newC});
                    }
                }
            }
        }
    }

    public static void gravity() {
        for (int i = 0; i < N; i++) {
            //한줄씩 검사
            for (int j = N - 1; j >= 0; j--) {
                if (map[j][i] != 0 && map[j][i] != -1) {
                    int bottom = j + 1;

                    while (true) {
                        if (bottom == N) break;

                        if (map[bottom][i] == 0) bottom++;
                        else break;
                    }

                    if (bottom == j + 1) continue;
                    map[bottom - 1][i] = map[j][i];
                    map[j][i] = 0;
                }
            }
        }
    }

    public static void rotate() {
        int[][] res = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int newI = N - 1 - j;
                int newJ = i;
                res[newI][newJ] = map[i][j];
            }
        }

        map = res;
    }
}

class Block implements Comparable<Block> {
    int startR, startC, cnt, rainbow;

    Block(int startR, int startC, int cnt, int rainbow) {
        this.startR = startR;
        this.startC = startC;
        this.cnt = cnt;
        this.rainbow = rainbow;
    }

    @Override
    public String toString() {
        return "Block{" +
                "startR=" + startR +
                ", startC=" + startC +
                ", cnt=" + cnt +
                ", rainbow=" + rainbow +
                '}';
    }

    @Override
    public int compareTo(Block b) {
        int comp = Integer.compare(b.cnt, cnt);
        if (comp == 0) {
            comp = Integer.compare(b.rainbow, rainbow);
        }
        if (comp == 0) {
            comp = Integer.compare(b.startR, startR);
        }
        if (comp == 0) {
            comp = Integer.compare(b.startC, startC);
        }
        return comp;
    }
}