package practice.p1615;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, S = 1;
    static int answer;
    static int[] indexTree;
    static int[][] command;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1615/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while (S < N) S *= 2;

        indexTree = new int[2 * S];
        command = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            command[i][0] = from;
            command[i][1] = to;
        }

        Arrays.sort(command, (a, b) -> {
            int comp = Integer.compare(a[0], b[0]);
            if (comp == 0) {
                comp = Integer.compare(a[1], b[1]);
            }
            return comp;
        });

        for (int[] c : command) {
            int from = c[0];
            int to = c[1];

            answer += query(1, S, 1, to + 1, N);
            update(1, S, 1, to, 1);
        }

        System.out.println(answer);
    }

    public static void update(int left, int right, int node, int target, int diff) {
        if (target < left || right < target) {
            return;
        } else {
            indexTree[node] += diff;
            if (left == right) return;
            else {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    public static int query(int left, int right, int node, int leftQuery, int rightQuery) {
        //범위 밖
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {
            return indexTree[node];
        } else {
            int mid = (left + right) / 2;
            int leftVal = query(left, mid, node * 2, leftQuery, rightQuery);
            int rightVal = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery);
            return leftVal + rightVal;
        }
    }
}