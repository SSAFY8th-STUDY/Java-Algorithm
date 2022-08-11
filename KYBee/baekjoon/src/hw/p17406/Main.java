package hw.p17406;

import java.util.*;
import java.io.*;

public class Main {
    public static final int[] dr = {0, 1, 0, -1};
    public static final int[] dc = {1, 0, -1, 0};

    static int N, M, K;
    static int[][] numArr;
    static int[][] command;
    static int[] output;
    static boolean[] isSelected;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numArr = new int[N + 1][M + 1];
        command = new int[K + 1][3];
        isSelected = new boolean[K + 1];
        output = new int[K];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                numArr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < 3; j++) {
                command[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getPerm(0);
        System.out.println(answer);
    }

    public static int[][] deepCopy(int[][] original) {
        int[][] res = new int[N + 1][];
        for (int i = 0; i <= N; i++) {
            res[i] = original[i].clone();
        }
        return res;
    }

    public static void calculate(int[][] arr) {
        for (int o : output) {
            int[] op = command[o];

            int centerR = op[0];
            int centerC = op[1];

            // 정사각형으로 나옴 3개 -> 5개 -> 7개 ..
            for (int s = 1; s <= op[2]; s++) {
                int curR = centerR - s;
                int curC = centerC - s;
                int before = arr[curR][curC];

                for (int dir = 0; dir < 4; dir++) {
                    for (int i = 0; i < 2 * s; i++) {
                        curR += dr[dir]; curC += dc[dir];
                        int temp = before;
                        before = arr[curR][curC];
                        arr[curR][curC] = temp;
                    }
                }
            }
        }
        int minValue = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            minValue = Math.min(minValue, getSum(arr[i]));
        }
        answer = Math.min(answer, minValue);
    }

    public static void getPerm(int cnt) {
        if (cnt == K) {
            calculate(deepCopy(numArr));
            return;
        }

        for (int i = 1; i <= K; i++) {
            if (isSelected[i] == false) {
                isSelected[i] = true;
                output[cnt] = i;
                getPerm(cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    public static int getSum(int[] arr) {
        int sum = 0;
        for (int a : arr) sum+= a;
        return sum;
    }
}