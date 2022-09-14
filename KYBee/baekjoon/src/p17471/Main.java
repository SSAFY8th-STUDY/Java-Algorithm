package p17471;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] people;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p17471/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            // 리스트로 받을꺼라 인접한 수는 필요 없음
            int M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < M; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph[to].add(i);
                graph[i].add(to);
            }
        }

        // 부분집합으로 두 구역을 나눔
        getSet(1, 0, 0);

        if (minValue == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minValue);
    }

    public static void getSet(int idx, int tCount, int fCount) {
        if (idx > N) {
            if (tCount >= 1 && fCount >= 1) {
                // 공집합이 아닐 때
                calculate();
            }
            return;
        }

        visited[idx] = true;
        getSet(idx + 1, tCount + 1, fCount);
        visited[idx] = false;
        getSet(idx + 1, tCount, fCount + 1);
    }

    public static void calculate() {
        boolean[] checked = new boolean[N + 1];
        int trueTeam = 0; int falseTeam = 0;

        // true인 친구들 다 체크 -> true인 친구 1개를 넣어줌
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                checked[i] = true;
                q.add(i);
                trueTeam = people[i];
                break;
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (Integer next : graph[current]) {
                if (visited[next] && !checked[next]) {
                    checked[next] = true;
                    trueTeam += people[next];
                    q.add(next);
                }
            }
        }


        // false인 친구들 다 체크 -> false인 친구 1개를 넣어줌
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                checked[i] = true;
                q.add(i);
                falseTeam = people[i];
                break;
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (Integer next : graph[current]) {
                if (!visited[next] && !checked[next]) {
                    checked[next] = true;
                    falseTeam += people[next];
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!checked[i]) return;
        }

        minValue = Math.min(minValue, Math.abs(trueTeam - falseTeam));
    }
}
