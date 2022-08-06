package p1021;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {

    static int N, M;
    static int totalCnt;
    static int[] outList;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p1021/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        outList = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            outList[i] = Integer.parseInt(st.nextToken());
        }

        dq.addAll(IntStream.rangeClosed(1, N).collect(ArrayDeque::new, Deque::add, Deque::addAll));

        int totalCnt = 0;

        System.out.println(dq);

        System.out.println(totalCnt);
    }
}
