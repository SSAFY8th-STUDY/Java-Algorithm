package practice.p7662;

import java.util.*;
import java.io.*;

public class Main {
    static int TC, N;
    static int cnt;
    static PriorityQueue<Integer> maxPQ;
    static PriorityQueue<Integer> minPQ;
    static Map<Integer, Integer> dict;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p7662/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            dict = new HashMap<>();
            maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
            minPQ = new PriorityQueue<>();

            cnt = 0;
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                switch(op) {
                    case "I":
                        maxPQ.add(value);
                        minPQ.add(value);
                        if (!dict.containsKey(value)) dict.put(value, 1);
                        else dict.put(value, dict.get(value) + 1);
                        cnt++;
                        break;
                    case "D":
                        if (cnt == 0) continue;
                        if (value > 0) {
                            while(!maxPQ.isEmpty() && dict.get(maxPQ.peek()) == 0) {
                                maxPQ.poll();
                            }
                            if (!maxPQ.isEmpty()){
                                int target = maxPQ.poll();
                                dict.put(target, dict.get(target) - 1);
                                cnt--;
                            }
                        } else {
                            while(!minPQ.isEmpty() && dict.get(minPQ.peek()) == 0) {
                                minPQ.poll();
                            }
                            if (!minPQ.isEmpty()){
                                int target = minPQ.poll();
                                dict.put(target, dict.get(target) - 1);
                                cnt--;
                            }
                        }
                        break;
                }
            }

            if (cnt == 0) System.out.println("EMPTY");
            else {
                while(!maxPQ.isEmpty() && dict.get(maxPQ.peek()) == 0) {
                    maxPQ.poll();
                }
                while(!minPQ.isEmpty() && dict.get(minPQ.peek()) == 0) {
                    minPQ.poll();
                }
                System.out.println(maxPQ.peek() + " " + minPQ.peek());
            }
        }
    }
}