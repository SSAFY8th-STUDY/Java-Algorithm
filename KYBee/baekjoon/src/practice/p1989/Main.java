//TODO -> 아직 못품

package practice.p1989;

import java.util.*;
import java.io.*;

public class Main {

    static int N, S;
    static int[] numList;
    static int[] maxTree;
    static long[] sumTree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1989/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        S = 1;
        while (S < N) S *= 2;

        numList = new int[N];
        maxTree = new int[2 * S];
        sumTree = new long[2 * S];

        for (int i = 0 ; i < N ; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            maxTree[S + i] = numList[i];
            sumTree[S + i] = numList[i];
        }

        for (int i = S - 1; i > 0; i--) {
            maxTree[i] = Math.max(maxTree[2 * i], maxTree[2 * i + 1]);
            sumTree[i] = sumTree[i * 2] + sumTree[i * 2 + 1];
        }


        System.out.println();

    }

    /**
     *
     * */
    public static void sumQuery(int left, int right, int node, int leftQuery, int rightQuery) {



    }

    /**
     * 1. 숫자가 노드보다 크다
     * 2. 숫자가 노드보다 작거나 같다.
     * */
    public static int maxQuery(int left, int right, int node, int target) {
        if (maxTree[node] < target) {
            return 0;
        } else if (left == right) {
            return node;
        } else {
            int mid = (left + right) / 2;
            int lIdx = maxQuery(left, mid, 2 * node, target);
            int rIdx = maxQuery(mid + 1, right, 2 * node + 1, target);
            return Math.max(lIdx, rIdx);
        }
    }
}
