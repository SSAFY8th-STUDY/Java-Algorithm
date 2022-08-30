package hw.p16236;

import java.io.*;
import java.util.*;

public class Main_BJ_16236_아기상어_김영빈 {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, answer;
    static int agiR, agiC;
    static int agiLevel = 2;
    static int agiFishToLevelUp = agiLevel;
    static int[] eatable = new int[7];
    static int[][] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p16236/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int currentFish = map[i][j] = Integer.parseInt(st.nextToken());
                if (currentFish == 9) {
                    agiR = i; agiC = j;
                    map[i][j] = 0;
                } else if (currentFish != 0) {
                    eatable[currentFish]++;
                }
            }
        }

        while (isEatable()) goAgi();
        System.out.println(answer);
    }

    public static void goAgi() {
        Queue<int[]> q = new ArrayDeque<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int comp = Integer.compare(a[2], b[2]);
            comp = comp == 0 ? Integer.compare(a[0], b[0]) : comp;
            return comp == 0 ? Integer.compare(a[1], b[1]) : comp;
        });

        int minDist = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[N][N];
        visited[agiR][agiC] = true;
        q.add(new int[] {agiR, agiC, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curR = current[0]; int curC = current[1]; int time = current[2];

            if (time > minDist) continue;

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                    if (!visited[newR][newC]) {
                        if (map[newR][newC] == 0 || map[newR][newC] == agiLevel) {
                            visited[newR][newC] = true;
                            q.add(new int[] {newR, newC, time + 1});
                        } else if (map[newR][newC] < agiLevel) {
                            minDist = Math.min(minDist, time + 1);
                            pq.add(new int[] {newR, newC, time + 1});
                        }
                    }
                }
            }
        }

        if (pq.isEmpty()) {
            Arrays.fill(eatable, 0);
        } else {
            int[] food = pq.poll();
            agiR = food[0]; agiC = food[1];
            eatable[map[agiR][agiC]]--;
            map[agiR][agiC] = 0;

            if (--agiFishToLevelUp == 0) {
                agiLevel++;
                agiFishToLevelUp = agiLevel;
            }
            answer += food[2];
        }
    }

    public static boolean isEatable() {
        int total = 0;
        for (int i = 1; i < Math.min(agiLevel, 7); i++) {
            total += eatable[i];
        }
        return total > 0;
    }
}