package hw.p2839;

import java.io.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        if (N == 4) System.out.println(-1);
        else if (N == 3 || N ==5) System.out.println(1);
        else {
            dp[3] = 1; dp[5] = 1;
            for (int i = 6; i <= N; i++) {
                int v1 = dp[i - 3];
                int v2 = dp[i - 5];
                if (v1 != 0 && v2 != 0) dp[i] = Math.min(v1, v2) + 1;
                else if (v1 == 0 && v2 == 0) continue;
                else dp[i] = Math.max(v1, v2) + 1;
            }
            System.out.println(dp[N] == 0 ? -1 : dp[N]);
        }
    }
}