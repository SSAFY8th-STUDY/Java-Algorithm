package practice.p7793;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dr = {-1 ,0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int TC;
    static int N, M;
    static char[][] graph;
    static int answer;
    static Queue<int[]> demons;
    static Queue<int[]> sooyeons;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p7793/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            demons = new ArrayDeque<>();
            sooyeons = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;

            graph = new char[N][M];
            for (int r = 0; r < N; r++) {
                String line = br.readLine();
                for (int c = 0; c < M; c++) {
                    graph[r][c] = line.charAt(c);
                    if (graph[r][c] == 'S') sooyeons.add(new int[] {r, c});
                    else if (graph[r][c] == '*') demons.add(new int[] {r, c});
                }
            }

            int time = 0;
            while (!sooyeons.isEmpty()) {
                time++;

                goDemon();
                if (goSooyeon()) {
                    answer = time;
                    break;
                }
            }

            sb.append("#" + tc + " " + (answer == Integer.MAX_VALUE ? "GAME OVER" : answer)).append("\n");
        }

        System.out.println(sb);
    }

    public static void goDemon() {
        Queue<int[]> newDemons = new ArrayDeque<>();

        while (!demons.isEmpty()) {
            int[] current = demons.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    if (graph[newR][newC] == 'X' || graph[newR][newC] == 'D') continue;
                    else if (graph[newR][newC] == 'S' || graph[newR][newC] == '.') {
                        graph[newR][newC] = '*';
                        newDemons.add(new int[] {newR, newC});
                    }
                }
            }
        }

        demons = newDemons;
    }

    public static boolean goSooyeon() {
        Queue<int[]> newSooyeons = new ArrayDeque<>();

        while (!sooyeons.isEmpty()) {
            int[] current = sooyeons.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < M) {
                    if (graph[newR][newC] == 'X' || graph[newR][newC] == '*') continue;
                    else if (graph[newR][newC] == 'D') {
                        return true;
                    } else if (graph[newR][newC] == '.') {
                        graph[newR][newC] = 'S';
                        newSooyeons.add(new int[] {newR, newC});
                    }
                }
            }
        }

        sooyeons = newSooyeons;
        return false;
    }
}
