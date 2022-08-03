package p11286;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue(new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            int a = (int) o1;
            int b = (int) o2;

            int comp = Math.abs(a) - Math.abs(b);
            if (comp == 0) {
                return a - b;
            }
            return comp;
        }
    });
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/p11286/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            if (op == 0) {
                if (pq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pq.poll());
                }
                sb.append("\n");
            } else {
                pq.add(op);
            }
        }
        System.out.println(sb.toString());
    }
}