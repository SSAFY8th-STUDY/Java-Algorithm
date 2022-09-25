package p20160;
import java.util.*;
import java.io.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static int V, E;
    static Map<Integer, Integer>[] graph;
    static int[] path;
    static long[] order;
    static long answer = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new Map[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new HashMap<>();
        }
        path = new int[V + 1];
        order = new long[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (!graph[u].containsKey(v) || (graph[u].containsKey(v) && graph[u].get(v) > w)) {
                graph[u].put(v, w);
                graph[v].put(u, w);
            }
        }

        // 야구루트 아줌마가 가는 길들 시간의 upper bound 확인
        st = new StringTokenizer(br.readLine());
        int before = Integer.parseInt(st.nextToken());
        int ajumma = before;
        for (int i = 1; i < 10; i++) {
            int next = Integer.parseInt(st.nextToken());
            // 둘 사이의 최단 거리 구하고
            int curTime = findPath(before, next);
            if (curTime == Integer.MAX_VALUE) continue;

            order[next] = curTime + order[before];
            before = next;
        }

        findPath(Integer.parseInt(br.readLine()), -1);

        for (int i = 1; i <= V; i++) {
            if (order[i] >= path[i] && (order[i] != 0 || ajumma == i)) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    public static int findPath(int s, int e) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[1], b[1]);
        });
        Arrays.fill(path, INF);
        path[s] = 0;
        pq.add(new int[] {s, 0});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            long weight = current[1];

            if (path[curNode] < weight) continue;

            for (Integer nextNode: graph[curNode].keySet()) {
                int nextWeight = graph[curNode].get(nextNode);
                int cost = path[curNode] + nextWeight;

                if (path[nextNode] > cost) {
                    path[nextNode] = cost;
                    pq.add(new int[] {nextNode, cost});
                }
            }
        }

        return e == -1 ? 0 : path[e];
    }
}