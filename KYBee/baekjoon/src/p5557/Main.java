package p5557;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] num;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p5557/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N];
        dp = new long[N + 1][21];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[1][num[0]] = 1;
        getSet(1);
    }

    public static void getSet(int cnt) {
        if (cnt == N - 1) {
            System.out.println(dp[cnt][num[N - 1]]);
            return;
        }

        for (int i = 0; i <= 20; i++) {
            if (dp[cnt][i] != 0) {
                if (i + num[cnt] <= 20)
                    dp[cnt + 1][i + num[cnt]] += dp[cnt][i];
                if (i - num[cnt] >= 0)
                    dp[cnt + 1][i - num[cnt]] += dp[cnt][i];
            }
        }
        getSet(cnt + 1);
    }
}