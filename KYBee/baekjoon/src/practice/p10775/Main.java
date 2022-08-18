package practice.p10775;

import java.util.*;
import java.io.*;

public class Main {

    static int G, P;
    static int[] parent;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p10775/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < P; i++) {
            int gate = find(Integer.parseInt(br.readLine()));
            if (find(gate) != 0) {
                union(gate, gate - 1);
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

    public static int find(int target) {
        if (parent[target] == target) {
            return target;
        } else {
            return parent[target] = find(parent[target]);
        }
    }
}
