package practice.p1068;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] tree;
    static boolean[] deleted;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1068/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new int[N + 1];
        deleted = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) tree[i] = Integer.parseInt(st.nextToken());

        System.out.println(Arrays.toString(tree));

        delete(Integer.parseInt(br.readLine()) + 1);
        System.out.println(Arrays.toString(deleted));

        System.out.println(count(1));
    }

    public static int count(int root) {
        if (root > N) {
            return 0;
        } else {
            if (deleted[root]) return 0;
            else if (2 * root > N) return 1;
            else return count(2 * root) + count(2 * root + 1);
        }
    }

    public static void delete(int node) {
        if (node > N) {
            return;
        } else {
            deleted[node] = true;
            delete(node * 2);
            delete(node * 2 + 1);
        }
    }
}

class Node {
    int left, right;

    public Node(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
