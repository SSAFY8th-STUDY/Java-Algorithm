package p11286;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return Math.abs(a) == Math.abs(b) ? Integer.compare(a, b) : Integer.compare(Math.abs(a), Math.abs(b));
    });

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/p11286/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                sb.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
            } else {
                pq.add(num);
            }
        }

        System.out.println(sb);
    }
}