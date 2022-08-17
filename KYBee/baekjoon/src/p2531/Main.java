package p2531;

import java.util.*;
import java.io.*;

public class Main {
    static int N, d, k, c;
    static int[] table;
    static Set<Integer> kMenu = new HashSet<>();
    static Map<Integer, Integer> menu = new HashMap<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p2531/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        table = new int[N];
        for (int i = 0; i < N; i++) {
            int curMenu = Integer.parseInt(br.readLine());
            table[i] = curMenu;
            menu.put(curMenu, 0);
        }

        for (int i = 0; i < k; i++) {
            kMenu.add(table[i]);
            menu.put(table[i], menu.get(table[i]) + 1);
        }
        answer = kMenu.contains(c) ? kMenu.size() : kMenu.size() + 1;

        int idx = 1;
        while (idx != 0) {

            int left = (idx - 1) % N;
            int right = (idx + k - 1) % N;

            if (table[left] != table[right]) {
                menu.put(table[left], menu.get(table[left]) - 1);
                if (menu.get(table[left]) == 0) {
                    kMenu.remove(table[left]);
                }

                menu.put(table[right], menu.get(table[right]) + 1);
                kMenu.add(table[right]);
            }

            idx = (idx + 1) % N;
            answer = Math.max(answer, kMenu.contains(c) ? kMenu.size() : kMenu.size() + 1);
        }

        System.out.println(answer);
    }
}