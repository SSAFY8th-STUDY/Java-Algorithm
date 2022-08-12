package p1228;

import java.util.*;
import java.io.*;

public class Solution_1228_암호문1_김영빈 {

    static final int TC = 10;
    static int N, M;
    static List<Integer> encrypted;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p1228/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= TC; tc++) {

            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            encrypted = new LinkedList<>();

            for (int i = 0 ; i < N; i++) {
                encrypted.add(Integer.parseInt(st.nextToken()));
            }

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                st.nextToken();
                int idx = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                List<Integer> newList = new LinkedList<>();

                for (int j = 0; j < count; j++) {
                    newList.add(Integer.parseInt(st.nextToken()));
                }

                encrypted.addAll(idx, newList);
            }

            sb.append("#" + tc + " ");
            for (int i = 0; i < 10; i++) {
                sb.append(encrypted.get(i) + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
