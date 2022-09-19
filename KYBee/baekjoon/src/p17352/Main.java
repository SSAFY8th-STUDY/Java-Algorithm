package p17352;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p17352/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= N - 2; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to);
        }

        int point1 = 1;
        int point2 = point1;

        for (int i = 2; i <= N; i++)
            if (point1 != find(parent[i])) {
                point2 = parent[i]; break;
            }

        System.out.println(1 + " " + point2);
    }

    public static void union(int a , int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent) parent[bParent] = aParent;
        else parent[aParent] = bParent;
    }

    public static int find (int target) {
        if (parent[target] == target) return target;
        else return parent[target] = find(parent[target]);
    }
}
