package practice.p23288;

import java.util.*;
import java.io.*;

public class Main {
    //동, 남, 서, 북
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};

    static int N, M, K;
    static int[][] graph;

    static int r, c, answer;

    //원래 자리 1 3 5
    static int[][] direction = {{4, 1, 5}, {2, 3, 1}, {3, 6, 5}, {5, 3, 6}};
    static int[] diceStatus = {0, 1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p23288/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dir = 0;
        while (K-->0) {
            // r, c 이동
            int newR; int newC;

            while (true) {
                newR = r + dr[dir];
                newC = c + dc[dir];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    break;
                } else {
                    dir = (dir + 2) % 4;
                }
            }

            rolling(dir);
            getScore(newR, newC);
            dir = setDirection(dir, newR, newC);

            r = newR;
            c = newC;
        }

        System.out.println(answer);
    }

    public static int setDirection(int dir, int newR, int newC) {
        int bottom = diceStatus[6];
        int map = graph[newR][newC];

        if (bottom > map) {
            return (dir + 1) % 4;
        } else if (bottom < map) {
            return  (3 + dir) % 4;
        } else {
            return dir;
        }
    }

    public static void rolling(int command) {
        int[] newStatus = new int[7];
        int[] dir = direction[command];

        //원래 1, 3, 5 위치에 있던 수가 어디로 이동하는지
        int one = dir[0];
        int three = dir[1];
        int five = dir[2];

        newStatus[1] = diceStatus[one];
        newStatus[2] = diceStatus[7 - five];
        newStatus[3] = diceStatus[three];
        newStatus[4] = diceStatus[7 - three];
        newStatus[5] = diceStatus[five];
        newStatus[6] = diceStatus[7 - one];

        diceStatus = newStatus.clone();
    }

    public static void getScore(int r, int c) {
        //bfs
        int cnt = 1;
        int target = graph[r][c];
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.add(new int[] {r, c});

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    if (!visited[newR][newC] && graph[newR][newC] == target) {
                        visited[newR][newC] = true;
                        q.add(new int[] {newR, newC});
                        cnt++;
                    }
                }
            }
        }

        answer += target * cnt;
    }
}
