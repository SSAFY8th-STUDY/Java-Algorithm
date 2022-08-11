package practice.p16975;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S;
    static long[] indexTree;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/practice/p16975/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S < N) {
            S *= 2;
        }
        indexTree = new long[2 * S];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; i++) {
            indexTree[S + i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                update(1, S, 1, from, to, weight);
            } else {
                int q = Integer.parseInt(st.nextToken());
                System.out.println(query(1, S, 1, q));
            }
        }
    }

    public static void update(int left, int right, int node, int leftQuery, int rightQuery, int diff) {
        //3가지 케이스가 존재
        //1. 둘다 아닌 경우
        //2. 둘 다 포함
        //3. 걸침
        if (right < leftQuery || rightQuery < left) {
            return;
        } else if (left == right && (left == leftQuery || right == rightQuery)) {
            indexTree[node] += diff;
        } else if (leftQuery <= left && right <= rightQuery) {
            indexTree[node] += diff;
        } else {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, leftQuery, rightQuery, diff);
            update(mid + 1, right, node * 2 + 1, leftQuery, rightQuery, diff);
        }
    }

    public static long query(int leftQuery, int rightQuery, int node, int target) {
        //1. 둘다 아닌 경우
        //2. 둘다 포함
        //3. 걸침
        if (target < leftQuery || rightQuery < target) {
            return 0;
        } else if (leftQuery == rightQuery && leftQuery == target) {
            return indexTree[node];
        } else {
            int mid = (leftQuery + rightQuery) / 2;
            long leftVal = query(leftQuery, mid, node * 2, target);
            long rightVal = query(mid + 1, rightQuery, node * 2 + 1, target);
            return leftVal + rightVal + indexTree[node];
        }
    }
}
