package hw.p1828;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Liquid[] liquids;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liquids = new Liquid[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int low = Integer.parseInt(st.nextToken());
            int high = Integer.parseInt(st.nextToken());
            liquids[i] = new Liquid(low, high);
        }

        Arrays.sort(liquids);
        int limit = liquids[0].high;
        int answer = 1;

        for (int i = 1; i < N; i++) {
            if (liquids[i].low > limit) {
                answer++; limit = liquids[i].high;
            }
        }
        System.out.println(answer);
    }
}

class Liquid implements Comparable<Liquid> {
    int low;
    int high;

    Liquid(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public int compareTo(Liquid l) {
        int comp = this.high - l.high;
        if (comp == 0) comp = this.low - l.low;
        return comp;
    }
}