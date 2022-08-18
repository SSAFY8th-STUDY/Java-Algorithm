package p14465;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K, B;
    static int[] light;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p14465/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        light = new int[N + 1];

        for (int i = 0; i < B; i++) {
            light[Integer.parseInt(br.readLine())] = 1;
        }

        for (int i = 1; i <= N; i++) {
            light[i] += light[i - 1];
        }

        int idx = K;
        while (idx <= N) {
            answer = Math.min(answer, light[idx] - light[idx - K]);
            idx++;
        }

        System.out.println(answer);
    }
}
