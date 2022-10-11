package p23291;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static int N, K;
    static int[] bowls;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p23291/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bowls = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bowls[i] = Integer.parseInt(st.nextToken());
        }

        int[] counting = bowls.clone();
        Arrays.sort(counting);
        int max = counting[N - 1];
        int min = counting[0];

        while(max - min > K) {
            addFish(min);
            ordering();
            reordering();
            answer++;

            counting = bowls.clone();
            Arrays.sort(counting);
            max = counting[N - 1];
            min = counting[0];
        }

        System.out.println(answer);
    }

    public static void addFish(int min) {
        for (int i = 0; i < N; i++) {
            if (bowls[i] == min) {
                bowls[i]++;
            }
        }
    }

    public static void ordering() {
        int remain = N - 1;
        int 세로 = 2;
        int 가로 = 1;

        int[][] tempBowl = new int[세로][remain];
        tempBowl[0][0] = bowls[0];
        for (int i = 0; i < N - 1; i++) {
            tempBowl[1][i] = bowls[i + 1];
        }

        while (세로 <= remain - 가로) {

            int[][] refactor = new int[가로 + 1][remain - 가로];

            for (int i = 0; i < 세로; i++) {
                for (int j = 0; j < 가로; j++) {
                    refactor[j][세로 - 1 - i] = tempBowl[i][j];
                }
            }

            for (int i = 0; i < remain - 가로; i++) {
                refactor[가로][i] = tempBowl[세로 - 1][가로 + i];
            }

            remain -= 가로;
            int 임시 = 가로;
            가로 = 세로;
            세로 = 임시 + 1;

            tempBowl = refactor;
        }

        // x, y, tox, toy, cnt;
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < 세로; i++) {
            for (int j = 0; j < remain; j++) {
                if (tempBowl[i][j] == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int newR = i + dr[d];
                    int newC = j + dc[d];
                    if (0 <= newR && newR < 세로 && 0 <= newC && newC < remain && tempBowl[newR][newC] != 0) {
                        int fish = tempBowl[i][j] - tempBowl[newR][newC];
                        if (fish < 5) continue;
                        else {
                            q.add(new int[] {i, j, newR, newC, fish / 5});
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int toX = current[2];
            int toY = current[3];
            int cnt = current[4];

            tempBowl[x][y] -= cnt;
            tempBowl[toX][toY] += cnt;
        }

        int cnt = 0;
        for (int i = 0; i < remain; i++) {
            for (int j = 세로 - 1; j >= 0; j--) {
                if (tempBowl[j][i] == 0) continue;
                bowls[cnt++] = tempBowl[j][i];
            }
        }
    }

    public static void reordering() {
        int[][] first = new int[2][N / 2];
        int[][] second = new int[4][N / 4];

        for (int i = 0; i < N / 2; i++) {
            first[1][i] = bowls[N / 2 + i];
            first[0][N / 2 - 1 - i] = bowls[i];
        }

        //공중에서 180도 ㅠㅠ
        for (int i = 0; i < N / 4; i++) {
            int temp = first[0][i];
            first[0][i] = first[1][N / 4 - 1 - i];
            first[1][N / 4 - 1 - i] = temp;
        }

        for (int i = 0; i < N / 4; i++) {
            second[0][i] = first[0][i];
            second[1][i] = first[1][i];
            second[2][i] = first[0][N / 4 + i];
            second[3][i] = first[1][N / 4 + i];
        }

        // x, y, tox, toy, cnt;
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N / 4; j++) {

                for (int d = 0; d < 4; d++) {
                    int newR = i + dr[d];
                    int newC = j + dc[d];
                    if (0 <= newR && newR < 4 && 0 <= newC && newC < N / 4) {
                        int fish = second[i][j] - second[newR][newC];
                        if (fish < 5) continue;
                        else {
                            q.add(new int[] {i, j, newR, newC, fish / 5});
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int toX = current[2];
            int toY = current[3];
            int cnt = current[4];

            second[x][y] -= cnt;
            second[toX][toY] += cnt;
        }

        int cnt = 0;
        for (int i = 0; i < N / 4; i++) {
            for (int j = 3; j >= 0; j--) {
                if (second[j][i] == 0) continue;
                bowls[cnt++] = second[j][i];
            }
        }
    }
}