package practice.p2573;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, M;
    static int[][] bingsan;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p2573/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bingsan = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                bingsan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int flag = findGroup();

            if (flag == 0) {
                answer = 0;
                break;
            } else if (flag >= 2) {
                break;
            }

            answer++;
            melt();
        }

        System.out.println(answer);
    }

    public static void melt() {
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (bingsan[i][j] != 0) {

                    int curR = i; int curC  = j;
                    int meltingCnt = 0;

                    for (int d = 0; d < 4; d++) {
                        int newR = curR + dr[d];
                        int newC = curC + dc[d];

                        if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                            if (bingsan[newR][newC] == 0) meltingCnt++;
                        }
                    }

                    if (meltingCnt > 0)
                        q.add(new int[] {i, j, meltingCnt});
                }
            }
        }

        while(!q.isEmpty()) {
            int[] current = q.poll();

            int r = current[0];
            int c = current[1];
            int meltingCnt = current[2];

            bingsan[r][c] -= meltingCnt;
            if (bingsan[r][c] < 0) bingsan[r][c] = 0;
        }
    }

    public static int findGroup() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int bingsanCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (bingsan[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    bingsanCnt++;

                    q.add(new int[] {i, j});

                    while(!q.isEmpty()) {
                        int[] current = q.poll();
                        int curR = current[0];
                        int curC = current[1];

                        for (int d = 0; d < 4; d++) {
                            int newR = curR + dr[d];
                            int newC = curC + dc[d];

                            if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                                if (!visited[newR][newC] && bingsan[newR][newC] != 0) {
                                    visited[newR][newC] = true;
                                    q.add(new int[] {newR, newC});
                                }
                            }
                        }
                    }
                }
            }
        }

        return bingsanCnt;
    }
}