package p1572;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K, S = 1;
    static int left = 1, right = 65537;
    static long answer = 0;
    static int[] indexTree;
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p1572/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while(S <= right) S *= 2;

        indexTree = new int[S * 2];

        for (int i = 1; i <= N; i++) {
            int target = Integer.parseInt(br.readLine()) + 1;
            q.add(target);

            update(left, S, 1, target, 1);

            if (i >= K) {
                int x = query(left, S, 1, (K + 1) / 2) - 1;
                answer += x;
                update(left, S, 1, q.poll(), -1);
            }
        }

        System.out.println(answer);
    }

    public static void update(int left, int right, int node, int target, int diff) {
        if (target < left || right < target) return;
        indexTree[node] += diff;

        if (left == right) return;

        int mid = (left + right) / 2;
        update(left, mid, node * 2, target, diff);
        update(mid + 1, right, node * 2 + 1, target, diff);
    }

    public static int query(int left, int right, int node, int cnt) {
        if (left == right) return left;

        int mid = (left + right) / 2;
        int leftNode = indexTree[node * 2];

        if (leftNode >= cnt) return query(left, mid, node * 2, cnt);
        else return query(mid + 1, right, node * 2 + 1, cnt - leftNode);
    }
}