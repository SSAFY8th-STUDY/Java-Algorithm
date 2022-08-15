package practice.p1854;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M, K;
    static PriorityQueue<Edge>[] dest;
    static ArrayList<Edge>[] graph;

    static int[] path;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/practice/p1854/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dest = new PriorityQueue[N + 1];
        graph = new ArrayList[N + 1];

        path = new int[N + 1];
        Arrays.fill(path, INF);

        for (int i = 1; i <= N; i++) {
            dest[i] = new PriorityQueue<>();
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, weight));
        }

        findPath(1);
        
        System.out.println(Arrays.toString(path));
    }

    public static void findPath(int root) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        path[root] = 0;
        pq.add(new Edge(root, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > path[current.to]) continue;

            for (Edge next: graph[current.to]) {
                int cost = current.weight + next.weight;
                if (cost < path[next.to]) {
                    path[next.to] = cost;
                    pq.add(new Edge(next.to, cost));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return e.weight - this.weight;
    }
}
