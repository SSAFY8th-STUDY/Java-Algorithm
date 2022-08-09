package p9229;

import java.io.*;
import java.util.*;

public class Main {

    static int TC;
    static int N, M;
    static int[] weights;
    static int answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p9229/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            weights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(weights);
            int front = 0, end = 0;
            answer = 0;
            while (front < end) {
                int tempWeight = weights[front] + weights[end];
                if (answer < tempWeight && tempWeight <= M) {
                    answer = tempWeight;
                    front++;
                } else if (tempWeight > M) {
                    end--;
                }
            }
        }

        System.out.println(answer);

    }

}
