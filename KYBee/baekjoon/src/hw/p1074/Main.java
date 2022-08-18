package hw.p1074;

import java.util.*;
import java.io.*;

public class Main {

    static long N = 1;
    static int r, c;
    static long order = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p1074/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        N = (long) Math.pow(2, n);

        find(r, c, N);
        System.out.println(order);
    }

    public static void find(long r, long c, long size) {
        if (size == 1) {
            return;
        }

        if (r < size / 2 && c < size / 2) {
            // 1사분면
            find(r, c, size / 2);
        } else if (r < size / 2 && c < size) {
            // 2사분면
            order += size * size / 4;
            find(r, c - size / 2, size / 2);
        } else if (r < size && c < size / 2) {
            // 3사분면
            order += size * size / 2;
            find(r - size / 2, c, size / 2);
        } else if (r < size && c < size) {
            // 4사분면
            order += size * size * 3 / 4;
            find(r - size / 2, c - size / 2, size / 2);
        }
    }
}