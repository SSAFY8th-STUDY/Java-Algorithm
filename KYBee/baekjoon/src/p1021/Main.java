package p1021;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {

    static int N, M;
    static int trial;
    static int totalCnt;
    static int[] outList;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p1021/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        outList = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            outList[i] = Integer.parseInt(st.nextToken());
        }

        q.addAll(IntStream.rangeClosed(1, N).collect(LinkedList::new, List::add, List::addAll));

        while (trial < M) {
            int current = outList[trial++];
            int cnt = 0;

            while (current != q.peek()) {
                q.offer(q.poll());
                //왼쪽으로 감
                cnt++;
            }

            totalCnt += Math.min(cnt, q.size() - cnt);

            q.poll();
        }

        System.out.println(totalCnt);
    }
}
