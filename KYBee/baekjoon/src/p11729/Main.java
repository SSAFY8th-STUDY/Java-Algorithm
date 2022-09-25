package p11729;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<int[]> result = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p11729/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        hanoi(N, 1, 2, 3);

        System.out.println(result.size());
        System.out.println(sb);
    }

    public static void hanoi(int N, int start, int mid, int end) {
        if (N == 1) {
            result.add(new int[]{start, end});
            sb.append(start +  " " + end + "\n");
            return;
        }

        hanoi(N - 1, start, end, mid);
        result.add(new int[] {start, end});
        sb.append(start +  " " + end + "\n");
        hanoi(N - 1, mid, start, end);
    }
}
