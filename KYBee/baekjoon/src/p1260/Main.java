package p1260;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, V;
    static List<Integer>[] graph;

    static boolean[] dfsVisited;
    static boolean[] bfsVisited;

    static StringBuilder sb = new StringBuilder();

    static List<Integer> dfsResult = new ArrayList<>();
    static List<Integer> bfsResult = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dfsVisited = new boolean[N + 1];
        bfsVisited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        dfs(V);

        for (int i = 0; i < dfsResult.size() ; i++) {
            sb.append(dfsResult.get(i)).append(" ");
        }
        sb.append("\n");

        bfs(V);

        for (int i = 0; i < bfsResult.size() ; i++) {
            sb.append(bfsResult.get(i)).append(" ");
        }
        sb.append("\n");

        System.out.println(sb.toString());
    }


    static void dfs(int start) {
        // 1. 체크인
        dfsVisited[start] = true;

        // 2. 목적지인가 ? => 목적지 딱히 없음 -> 하지만 매 노드마다 저장해야함
        dfsResult.add(start);

        // 3. 인접한 노드 검색
        for (int node : graph[start]) {
            // 4. 갈 수 있는가?
            if (dfsVisited[node] == false) {

                // 5. 간다
                dfs(node);
            }
        }
        // 6. 체크아웃은 없어야 함
    }

    static void bfs(int start) {

        //0. 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        bfsVisited[start] = true;

        while (!queue.isEmpty()) {
            //1. 큐에서 꺼냄
            int current = queue.poll();

            //2. 목적지인가 ? => 목적지 딱히 없음 -> 하지만 매 노드마다 저장해야함
            bfsResult.add(current);

            // 3. 인접한 노드 검색
            for (int node : graph[current]) {
                //4. 갈 수 있는가?
                if (bfsVisited[node] == false) {
                    // 4-1. 체크인
                    bfsVisited[node] = true;
                    // 4-2. 큐에 넣음
                    queue.add(node);
                }
            }
        }
    }
}
