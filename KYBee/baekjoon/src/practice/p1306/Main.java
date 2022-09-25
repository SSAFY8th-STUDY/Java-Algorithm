package practice.p1306;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[] indexTree;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/practice/p1306/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        K = 1;
        while (K < N) {
            K *= 2;
        }

        indexTree = new int[2 * K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            indexTree[K + i] = Integer.parseInt(st.nextToken());
        }

        for (int i = K - 1; i > 0; i--) {
            indexTree[i] = Math.max(indexTree[2 * i], indexTree[2 * i + 1]);
        }

        for (int i = M; i <= N - M + 1; i++) {
            sb.append(query(1, K, 1, i - (M - 1), i + (M - 1))).append(" ");
        }
        System.out.println(sb);
    }

    public static int query(int left, int right, int node, int leftQuery, int rightQuery) {

        if (rightQuery < left || right < leftQuery) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return indexTree[node];
        } else {
            int mid = (left + right) / 2;
            int leftValue = query(left, mid, node * 2, leftQuery, rightQuery);
            int rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return Math.max(leftValue, rightValue);
        }
    }
}