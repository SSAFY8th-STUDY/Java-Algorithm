package practice.p1167;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static HashMap<Integer, Integer>[] graph;
    static boolean[] visited;
    static PriorityQueue<Integer[]> pq;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/practice/p1167/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new HashMap[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new HashMap<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                int weight = 0;
                if (node == -1) {
                    break;
                } else {
                    weight = Integer.parseInt(st.nextToken());
                }
                graph[v].put(node, weight);
            }
        }

        int[] result = new int[2];
        int start = 1;
        for (int i = 0; i < 2; i++) {
            pq = new PriorityQueue<>((o1, o2) -> {
                return o2[0] - o1[0];
            });
            visited = new boolean[N + 1];

            dfs(start, 0);
            start = pq.peek()[1];
            result[i] = start;
        }
        visited = new boolean[N + 1];
        dfs(result[0], 0, result[1]);
    }

    public static void dfs(int n, int weight) {
        HashMap<Integer, Integer> map = graph[n];
        visited[n] = true;

        pq.add(new Integer[] {weight, n});

        for (Integer start: map.keySet()) {
            if (!visited[start])
                dfs(start, weight + map.get(start));
        }
    }


    public static void dfs(int n, int weight, int e) {
        HashMap<Integer, Integer> map = graph[n];
        visited[n] = true;

        if (n == e) {
            System.out.println(weight);
            return;
        }

        for (Integer start: map.keySet()) {
            if (!visited[start])
                dfs(start, weight + map.get(start), e);
        }
    }
}
