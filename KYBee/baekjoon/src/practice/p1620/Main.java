package practice.p1620;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static Map<Integer, String> itos = new HashMap<>();
    static Map<String, Integer> stoi = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1620/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int idx = 1;
        for (int i = 0; i < N; i++) {
            String target = br.readLine();
            if (!stoi.containsKey(target)) {
                stoi.put(target, idx);
                itos.put(idx++, target);
            }
        }

        for (int i = 0; i < M; i++) {
            String target = br.readLine();
            if (!stoi.containsKey(target)) {
                System.out.println(itos.get(Integer.parseInt(target)));
            } else {
                System.out.println(stoi.get(target));
            }
        }
    }
}
