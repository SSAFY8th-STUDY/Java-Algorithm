package hw.p2961;

import java.util.*;
import java.io.*;

public class Main_2961_도영이가만든맛있는음식_김영빈 {
    static int N;
    static int[][] ingredients;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p2961/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ingredients = new int[N][2];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken());
            ingredients[i][1] = Integer.parseInt(st.nextToken());
        }

        binaryCounting();
        System.out.println(answer);
    }

    public static void binaryCounting() {
        for (int flag = 1, cnt = 1 << N; flag < cnt; flag++) {
            int sin = 1;
            int seun = 0;
            for (int i = 0; i <= N; i++) {
                if ((flag & (1 << i)) != 0) {
                    sin *= ingredients[i][0];
                    seun += ingredients[i][1];
                }
            }
            answer = Math.min(answer, Math.abs(sin - seun));
        }
    }
}
