package practice.p13537;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, S;
    static int[] numList;
    static List<Integer>[] indexTree;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p13537/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = 1;
        while (S < N) S *= 2;

        numList = new int[N];
        indexTree = new List[2 * S];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            indexTree[S + i] = new ArrayList<>();
            indexTree[S + i].add(numList[i]);
        }

        for (int i = N; i < S; i++) {
            indexTree[S + i] = new ArrayList<>();
        }

        for (int i = S - 1; i > 0; i--) {
            indexTree[i] = new ArrayList<>();
            indexTree[i].addAll(indexTree[2 * i]);
            indexTree[i].addAll(indexTree[2 * i + 1]);
            Collections.sort(indexTree[i]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            System.out.println(query(1, S, 1, left, right, k));
        }
    }

    public static int query(int left, int right, int node, int leftQuery, int rightQuery, int k) {
        if (right < leftQuery || rightQuery < left) {
            return 0;
        } else if (leftQuery <= left && right <= rightQuery) {

            int l = 0, r = indexTree[node].size();
            while (l < r) {
                int mid = (l + r) / 2;
                if (indexTree[node].get(mid) <= k) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return indexTree[node].size() - r;

        } else {

            int mid = (left + right) / 2;
            int leftValue = query(left, mid, node * 2, leftQuery, rightQuery, k);
            int rightValue = query(mid + 1, right, node * 2 + 1, leftQuery, rightQuery, k);
            return leftValue + rightValue;

        }
    }
}

