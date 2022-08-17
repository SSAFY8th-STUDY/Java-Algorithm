package p1806;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long S;
    static long[] numList;
    static long answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numList = new long[N + 1];
        answer = Long.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Long.parseLong(st.nextToken());
        }

        int start = 0, end = 0;
        long current = numList[end];

        while (start < N && end < N && start <= end) {
            if (current < S) {
                current += numList[++end];
            } else {
                answer = Math.min(answer, end - start + 1);
                current -= numList[start++];
            }
        }

        System.out.println(answer == Long.MAX_VALUE ? 0 : answer);
    }
}
