package practice.neonsign;

import java.util.*;
import java.io.*;

public class Solution {
    static final int[] dr = {0, -1, 0, 1};
    static final int[] dc = {-1, 0, 1, 0};

    static int N;
    static int answer;
    static int group = 10;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/neonsign/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N ;i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        System.out.println();
        int[][] visited = bfs(map, false);
        for (int i = 0; i < N ;i++) {
            System.out.println(Arrays.toString(visited[i]));
        }

        System.out.println();
        int[][] result = bfs(visited, true);
        for (int i = 0; i < N ;i++) {
            System.out.println(Arrays.toString(result[i]));
        }

    }

    public static int calHarmony() {

        return 0;
    }

    public static int[][] bfs(int[][] map, boolean op) {
        int[][] visited = new int[N][N];
        int[][] result = new int[group + 1][group + 1];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[] {i, j});
                    visited[i][j] = ++idx;

                    while (!q.isEmpty()) {
                        int[] current = q.poll();
                        int r = current[0];
                        int c = current[1];
                        int cur = map[r][c];

                        for (int d = 0; d < 4; d++) {
                            int newR = r + dr[d];
                            int newC = c + dc[d];

                            if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                                if(visited[newR][newC] == 0){
                                    int next = map[newR][newC];
                                    if (cur != next) {
                                        result[cur][next]++;
                                    } else {
                                        visited[newR][newC] = idx;
                                        q.add(new int[] {newR, newC});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        group = idx;

        if (op) return result;
        else return visited;
    }

    public static void rotatePlus() {

    }

    public static void rotateSquare() {

    }
}
