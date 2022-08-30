package practice.p15172;

import java.io.*;
import java.util.*;

public class Solution {
    static int TC, N, M;
    static List<int[]> points;
    static int[] order;
    static boolean[] client;
    static boolean[] monster;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p15172/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TC  = Integer.parseInt(br.readLine());
        for (int  tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            client = new boolean[5];

            answer = Integer.MAX_VALUE;
            points = new ArrayList<>();

            monster = new boolean[5];
            client = new boolean[5];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0) {
                        points.add(new int[] {i, j});
                        monster[Math.abs(map[i][j])] = true;
                        client[Math.abs(map[i][j])] = true;
                    }
                }
            }

            M = points.size();
            order = new int[M];

            goHunter(0, 0);
            sb.append("#" + tc + " " + answer + "\n");
        }
        System.out.println(sb);
    }

    public static boolean isComplete(boolean[] monster, boolean[] client) {
        for (int i = 1; i <= 4; i++) if (monster[i] == true || client[i] == true) return false;
        return true;
    }

    public static void goHunter(int cnt, int flag) {
        if (cnt == M) {
            boolean[] curMonster = monster.clone();
            boolean[] curClient = client.clone();

            int value = 0;
            int[] before = new int[] {0, 0};
            for (int i = 0; i < M; i++) {
                int[] curPoint = points.get(order[i]);
                int curElement = map[curPoint[0]][curPoint[1]];

                if (curElement > 0) {
                    curMonster[curElement] = false;
                } else {
                    if (curMonster[-1 * curElement]) break;
                    else curClient[-1 * curElement] = false;
                }

                value += Math.abs(before[0] - curPoint[0]) + Math.abs(before[1] - curPoint[1]);
                before = curPoint;
            }

            if (isComplete(curMonster, curClient)) {
                answer = Math.min(answer, value);
            }
            return;
        }

        for (int i = 0; i < M; i++) {
            if ((flag & (1 << i)) == 0) {
                order[cnt] = i;
                goHunter(cnt + 1, flag | (1 << i));
            }
        }
    }
}