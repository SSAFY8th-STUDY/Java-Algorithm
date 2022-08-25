package hw.p1238;

import java.util.*;
import java.io.*;

public class Solution_1238_Contact_김영빈 {
    public static int TC = 10;
    public static int MAX_N = 100;
    public static int N;
    public static int D, S;
    public static List<Integer>[] map;
    public static boolean[] visited;
    public static Map<Integer, Integer> level;
    public static int maxLevel;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            map = new ArrayList[MAX_N + 1];
            visited = new boolean[MAX_N + 1];
            level = new HashMap<>();
            maxLevel = 0;

            for (int i = 1; i <= MAX_N; i++) {
                map[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < D / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                map[from].add(to);
            }

            findPath(S);
            System.out.format("#%d %d\n", tc + 1, level.get(maxLevel));
        }
    }

    public static void findPath(int start) {
        Queue<Integer[]> q = new LinkedList<>();
        visited[start] = true;
        q.add(new Integer[] {start, 0});
        level.put(0, start);

        while (!q.isEmpty()) {
            Integer[] current = q.poll();
            int currentLevel = current[1];

            for (int next: map[current[0]]) {
                if (visited[next] == false) {
                    visited[next] = true;
                    q.add(new Integer[] {next, currentLevel + 1});
                    if (level.containsKey(currentLevel + 1)) {
                        level.replace(currentLevel + 1, Math.max(level.get(currentLevel + 1), next));
                    } else {
                        level.put(currentLevel + 1, next);
                    }
                    maxLevel = Math.max(maxLevel, currentLevel + 1);
                }
            }
        }
    }
}