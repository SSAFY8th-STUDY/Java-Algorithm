package practice.p1967;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Edge>[] graph;
    static int[] result;
    static boolean[] visited;

    static int maxWeight = 0;
    static int root = 1;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1967/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        // 임의의 노드에서 가장 먼 노드를 일단 찾음
        // 그 다음 두 번째 dfs
        for (int i = 0; i < 2; i++) {
            result = new int[N + 1];
            visited = new boolean[N + 1];
            dfs(new Edge(root, 0), 0);
        }
        System.out.println(maxWeight);
    }

    public static void dfs(Edge current, int weight) {
        int node = current.to;
        visited[node] = true;
        result[node] = weight;
        if (weight > maxWeight) {
            maxWeight = weight;
            root = node;
        }

        for (Edge next: graph[node]) {
            if (!visited[next.to]) {
                dfs(next, next.weight + weight);
            }
        }
    }
}

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
