package p25187;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, Q;
    static int[] parent;
    static int[] whichWater;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p25187/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        whichWater = new int[N + 1];

        for (int i = 1 ; i <= N ; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) whichWater[i] = Integer.parseInt(st.nextToken()) == 1 ? 1 : -1;

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (find(from) != find(to)) union(from, to);
        }

        for (int i = 0; i < Q; i++) {
            int next = Integer.parseInt(br.readLine());
            System.out.println(whichWater[parent[next]] > 0 ? 1 : 0);
        }
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent) {
            parent[bParent] = aParent;
            whichWater[aParent] += whichWater[bParent];
        }
        else if (bParent < aParent) {
            parent[aParent] = bParent;
            whichWater[bParent] += whichWater[aParent];
        }
    }

    public static int find(int target) {
        if (parent[target] == target) return target;
        else return parent[target] = find(parent[target]);
    }
}
