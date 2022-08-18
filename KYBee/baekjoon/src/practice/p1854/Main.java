package practice.p1854;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static PriorityQueue<Integer>[] dest;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1854/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dest = new PriorityQueue[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            dest[i] = new PriorityQueue<>(Comparator.reverseOrder());
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

        for (int i = 1; i <= N ; i++) {
            if (dest[i].size() < K) {
                sb.append("-1\n");
            } else {
                sb.append(dest[i].peek()).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void findPath(int root) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dest[root].add(0);
        pq.add(new Edge(root, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            for (Edge next: graph[current.to]) {
                if (dest[next.to].size() < K || dest[next.to].peek() > current.weight + next.weight) {
                    if (dest[next.to].size() == K) dest[next.to].poll();
                    dest[next.to].add(current.weight + next.weight);
                    pq.add(new Edge(next.to, current.weight + next.weight));
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
        return this.weight - e.weight;
    }
}
