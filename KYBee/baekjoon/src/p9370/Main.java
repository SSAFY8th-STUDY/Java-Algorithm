package p9370;

import java.util.*;
import java.io.*;

public class Main {

    static final int INF = Integer.MAX_VALUE - 1;

    static int T;
    static int n, m, t;
    static int s, g, h;

    static List<int[]>[] graph;
    static int[] path;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p9370/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            path = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            Arrays.fill(path, INF);

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = 2 * Integer.parseInt(st.nextToken());

                if ((from == g && to == h) || (from == h && to == g)) {
                    weight -= 1;
                }

                graph[from].add(new int[] {to, weight});
                graph[to].add(new int[] {from, weight});
            }

            findPath(s);
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < t; i++) {
                //g - h 길을 거치는지 확인
                int possibleEnd = Integer.parseInt(br.readLine());
                if (path[possibleEnd] % 2 != 0) {
                    result.add(possibleEnd);
                }
            }

            Collections.sort(result);
            for (Integer n : result) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void findPath(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        path[start] = 0;
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curPos = current[0];
            int curWeight = current[1];

            if (curWeight > path[curPos]) continue;

            for (int[] next: graph[curPos]) {
                int cost = curWeight + next[1];

                if (path[next[0]] > cost) {
                    path[next[0]] = cost;
                    pq.add(new int[]{next[0], cost});
                }
            }
        }
    }
}
