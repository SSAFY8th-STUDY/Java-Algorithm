package p2887;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static long[][] X;
    static long[][] Y;
    static long[][] Z;

    static int[] parent;
    static long answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p2887/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        X = new long[N][2];
        Y = new long[N][2];
        Z = new long[N][2];

        parent = new int[N];
        for (int i = 1; i < N; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            X[i][0] = i; X[i][1] = Long.parseLong(st.nextToken());
            Y[i][0] = i; Y[i][1] = Long.parseLong(st.nextToken());
            Z[i][0] = i; Z[i][1] = Long.parseLong(st.nextToken());
        }

        //X, Y, Z
        Arrays.sort(X, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[1], o2[1]);
            }
        });

        Arrays.sort(Y, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[1], o2[1]);
            }
        });

        Arrays.sort(Z, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[1], o2[1]);
            }
        });

        int node = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[2], b[2]);
        });

        for (int i = 0; i < N - 1; i++) {
            // X Y Z 3개 넣기
            pq.add(new long[] {X[i][0], X[i + 1][0], X[i + 1][1] - X[i][1]});
            pq.add(new long[] {Y[i][0], Y[i + 1][0], Y[i + 1][1] - Y[i][1]});
            pq.add(new long[] {Z[i][0], Z[i + 1][0], Z[i + 1][1] - Z[i][1]});
        }

        while (node < N - 1) {
            // 가장 작은거 뽑고 MST
            long[] current = pq.poll();

            int a = (int) current[0];
            int b = (int) current[1];
            long weight = current[2];


            if (find(a) != find(b)) {
                node++;
                union(a, b);
                answer += weight;
            }
        }

        System.out.println(answer);
    }

    public static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else if (parentA > parentB) {
            parent[parentA] = parentB;
        }
    }

    public static int find(int target) {
        if (target == parent[target]) return target;
        else return parent[target] = find(parent[target]);
    }
}