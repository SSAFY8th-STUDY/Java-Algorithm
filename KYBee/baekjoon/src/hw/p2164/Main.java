package hw.p2164;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p2164/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        q.addAll(IntStream.rangeClosed(1, N).collect(ArrayList::new, List::add, List::addAll));
        while (q.size() != 1) {
            q.poll();
            q.offer(q.poll());
        }
        System.out.println(q.poll());
    }
}
