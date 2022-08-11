package p1021;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class MainDeque {
    static int N, M;
    static int trial;
    static int totalCnt;
    static int[] outList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> dq = new LinkedList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        outList = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            outList[i] = Integer.parseInt(st.nextToken());
        }

        dq.addAll(IntStream.rangeClosed(1, N).collect(LinkedList::new, List::add, List::addAll));

        while (trial < M) {
            int current = outList[trial++];
            int cnt = 0;

            while (current != dq.peek()) {
                dq.add(dq.pollFirst());
                //왼쪽으로 감
                cnt++;
            }
            totalCnt += Math.min(cnt, dq.size() - cnt);
            dq.pop();
        }
        System.out.println(totalCnt);
    }
}